package com.warehouse.work.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.warehouse.work.entity.OrderStatus;
import com.warehouse.work.service.OrderStatusService;

@Controller
@RequestMapping("/orderStatus")
@RequiredArgsConstructor
public class OrderStatusController {

    private final OrderStatusService service;

    @GetMapping
    String get(Model model) {
        List<OrderStatus> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "orderStatus-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            OrderStatus entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new OrderStatus());
        }
        return "orderStatus-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(OrderStatus entity) throws Exception {
        service.create(entity);
        return "redirect:/orderStatus";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/orderStatus";
    }
}
