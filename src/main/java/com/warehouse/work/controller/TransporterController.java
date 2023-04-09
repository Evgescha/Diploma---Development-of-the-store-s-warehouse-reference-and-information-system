package com.warehouse.work.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.warehouse.work.entity.Transporter;
import com.warehouse.work.service.InsuranceCompanyService;
import com.warehouse.work.service.TransporterService;

@Controller
@RequestMapping("/transporter")
@RequiredArgsConstructor
public class TransporterController {

    private final TransporterService service;

    private final InsuranceCompanyService insuranceCompanyService;

    @GetMapping
    String get(Model model) {
        List<Transporter> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "transporter-list";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            Transporter entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Transporter());
        }
        model.addAttribute("insuranceCompanies", insuranceCompanyService.repository.findAll());
        return "transporter-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Transporter entity, @Param("insuranceCompanyId") Long insuranceCompanyId) throws Exception {
        entity.setInsuranceCompany(insuranceCompanyService.read(insuranceCompanyId));
        service.create(entity);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/admin";
    }
}
