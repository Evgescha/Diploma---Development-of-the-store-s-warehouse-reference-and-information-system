package com.warehouse.work.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import com.warehouse.work.entity.OrderItem;
import com.warehouse.work.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.warehouse.work.entity.Order;
import com.warehouse.work.entity.Product;
import com.warehouse.work.entity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Transactional
public class UserController {
    private final UserServiceImpl service;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final TransporterService transporterService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;
    private final OrderStatusService orderStatusService;


    @GetMapping
    String get(Model model) {
        List<User> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

    @GetMapping("/basket")
    String getBasket(Model model, Principal principal) {
        model.addAttribute("entity", service.findByUsername(principal.getName()));
        model.addAttribute("transporters", transporterService.repository.findAll());
        return "basket";
    }

    @GetMapping("/history")
    String getHistory(Model model, Principal principal) {
        model.addAttribute("entity", service.findByUsername(principal.getName()));
        return "history";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            User entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new User());
        }
        return "user-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(User entity) throws Exception {
        if (entity.getId() == null)
            service.userRegistration(entity);
        else {
            User read = service.read(entity.getId());
            read.update(entity.getFio(), entity.getUsername(), entity.getPassword(), entity.getDateBorn(),
                    entity.getEmail(), entity.getPhone());
            read.setPassword(passwordEncoder.encode(read.getPassword()));
            service.update(read);
        }
        return "redirect:/admin#user";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin#user";
    }

    @RequestMapping(path = "/addProduct/{id}", method = RequestMethod.GET)
    public String addFoodInOrder(Principal principal,
                                 @PathVariable("id") Long id,
                                 @RequestParam Integer count,
                                 RedirectAttributes ra) {
        User user = service.findByUsername(principal.getName());
        //проверка, есть ли не оформленный заказ
        boolean isHas = false;
        Order order = null;
        for (Order tmp : user.getMyOrders()) {
            if (tmp.getStatus().getId() == 1) {
                isHas = true;
                order = tmp;
                break;
            }
        }

        // если заказа нет, перебросить на страницу бронирования
        if (!isHas) {
            Order temp = new Order();
            temp.setCreator(user);
            temp.setStatus(orderStatusService.read(1));
            Date dat = new Date(System.currentTimeMillis());
            orderService.create(temp);

            order = orderService.repository.findByCreatorAndStatusAndDateOrder(user, orderStatusService.read(1), null);
            user.getMyOrders().add(order);
            temp.setDateOrder(dat);
            service.update(user);

            user = service.read(user.getId());
            order = user.getMyOrders().get(user.getMyOrders().size() - 1);
            orderService.update(order);
        }
        order = orderService.read(order.getId());
        Product product = productService.read(id);
        int countResult = product.getCount() - count;
        if (countResult < 0) {
            count = product.getCount();
        }
        OrderItem orderItem = new OrderItem(order, product, count);
        orderItem = orderItemService.create(orderItem);
        order.getOrderElements().add(orderItem);
        if (orderService.update(order)) {
            Product pr = product;
            pr.setCount(product.getCount()-count);
            productService.update(pr);
        }
        return "redirect:/user/basket";
    }

    @RequestMapping(path = "/deleteProduct/{id}", method = RequestMethod.GET)
    public String deleteFoodFromOrder(Principal principal, @PathVariable("id") Long id) throws Exception {
        User user = service.findByUsername(principal.getName());
        //проверка, есть ли не оформленный заказ
        Order order = user.getMyOrders().get(user.getMyOrders().size() - 1);
        OrderItem localItem = order.getOrderElements().stream().filter(orderItem -> orderItem.getId() == id).findFirst().get();
        order.getOrderElements().remove(localItem);
        orderService.update(order);
        OrderItem orderItem = orderItemService.read(id);
        Product product = productService.read(orderItem.getProduct().getId());
        product.setCount(product.getCount()+orderItem.getCount());
        productService.update(product);
        orderItem.setOrder(null);
        orderItem.setProduct(null);
        orderItemService.update(orderItem);
        orderItemService.delete(orderItem.getId());
        return "redirect:/user/basket";
    }

    @RequestMapping(path = "/deleteOrder/{id}", method = RequestMethod.GET)
    public String deleteOrder(Principal principal, @PathVariable("id") Long id) throws Exception {
        User user = service.findByUsername(principal.getName());
        for (Order order : user.getMyOrders()) {
            if (order.getId() == id) {
                user.getMyOrders().remove(order);
                service.update(user);
                orderService.delete(id);
                break;
            }
        }
        return "redirect:/product";
    }

    @RequestMapping(path = "/confirm/{id}", method = RequestMethod.POST)
    public String confirmOrder(Principal principal, @PathVariable("id") Long id,
                               @Param("dateDelivery") Date dateDelivery, @Param("transporterId") Long transporterId) {
        //проверка, есть ли не оформленный заказ
        Order order = orderService.read(id);
        order.setDateDelivery(dateDelivery);
        order.setDateOrder(new Date(System.currentTimeMillis()));
        order.setTransporter(transporterService.read(transporterId));
        //устанавливае статус
        order.setStatus(orderStatusService.read(2));
        orderService.update(order);
        return "redirect:/user/history";
    }

}
