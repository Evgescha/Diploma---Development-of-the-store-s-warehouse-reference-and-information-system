package com.warehouse.work.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.warehouse.work.entity.Product;
import com.warehouse.work.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    String get(Model model) {
        List<Product> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "product-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            Product entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Product());
        }
        return "product-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Product entity) {
        service.create(entity);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin";
    }
}
