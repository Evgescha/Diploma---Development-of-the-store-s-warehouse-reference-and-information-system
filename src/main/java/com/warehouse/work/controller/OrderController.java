package com.warehouse.work.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.warehouse.work.entity.Order;
import com.warehouse.work.service.OrderService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    String get(Model model) {
        List<Order> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "order-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            Order entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Order());
        }
        return "order-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Order entity) throws Exception {
        service.create(entity);
        return "redirect:/order ";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/order";
    }
}
