package com.warehouse.work.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warehouse.work.entity.Order;
import com.warehouse.work.service.InsuranceCompanyService;
import com.warehouse.work.service.OrderService;
import com.warehouse.work.service.OrderStatusService;
import com.warehouse.work.service.ProductService;
import com.warehouse.work.service.TransporterService;
import com.warehouse.work.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final InsuranceCompanyService insuranceCompanyService;
    private final OrderStatusService orderStatusService;
    private final ProductService productService;
    private final UserServiceImpl userService;
    private final TransporterService transporterService;
    private final OrderService orderService;

    @GetMapping
    @RequestMapping
    String getIndex(Model model) {
        model.addAttribute("statuses", orderStatusService.repository.findAll());
        model.addAttribute("products", productService.repository.findAll());
        model.addAttribute("insuranceCompanies", insuranceCompanyService.repository.findAll());
        model.addAttribute("transporters", transporterService.repository.findAll());
        model.addAttribute("users", userService.repository.findAll());
        return "admin";
    }

    @GetMapping
    @RequestMapping("/orders")
    String getBooking(Model model) {
        model.addAttribute("list", orderService.repository.findAll());
        return "adminOrders";
    }

    @GetMapping
    @RequestMapping("/confirmOrder/{id}")
    String confirmOrder(Model model, @PathVariable("id") Long id) {
        Order order = orderService.read(id);
        order.setStatus(orderStatusService.read(3));
        orderService.update(order);
        return "redirect:/admin/orders";
    }

    @GetMapping
    @RequestMapping("/completeOrder/{id}")
    String conpleteOrder(Model model, @PathVariable("id") Long id) {
        Order order = orderService.read(id);
        order.setStatus(orderStatusService.read(4));
        orderService.update(order);
        return "redirect:/admin/orders";
    }

    @GetMapping
    @RequestMapping("/deleteOrder/{id}")
    String deleteOrder(Model model, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.read(id);
        order.getOrderElements().clear();
        orderService.update(order);
        orderService.delete(id);
        return "redirect:/admin/orders";
    }
}
