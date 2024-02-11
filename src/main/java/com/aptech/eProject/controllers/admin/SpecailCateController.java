package com.aptech.eProject.controllers.admin;

import com.aptech.eProject.models.SpecialCategory;
import com.aptech.eProject.services.SpecailCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/specailcategories")
public class SpecailCateController {
    @Autowired
    SpecailCategoryService specailCategoryService;

    @GetMapping("")
    public ModelAndView index(ModelAndView model) {
        model.addObject("specailcates", specailCategoryService.getAll());
        model.setViewName("admin/specailcate/index");
        return model;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        specailCategoryService.delete(Integer.parseInt(id));
        return "redirect:/admin/specailcategories";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView model, @PathVariable String id) {
        model.addObject("specailcate", specailCategoryService.detail(Integer.parseInt(id)));
        model.setViewName("admin/specailcate/edit");
        return model;
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable String id, @Valid SpecialCategory specialCategory, BindingResult result) {
        SpecialCategory detail =  specailCategoryService.detail(Integer.parseInt(id));
        SpecialCategory existingCategory = specailCategoryService.findCategoryByName(specialCategory.getName());
        if (existingCategory != null && existingCategory.getName() != null
                && !existingCategory.getName().isEmpty()) {
            result.rejectValue("name", null,
                    "There is already an account registered with the same name");
        }
        if (result.hasErrors() || detail == null ) {
            model.addAttribute("specialCategory", specialCategory);
            return "admin/specailcate/edit";
        }

        SpecialCategory existed = specailCategoryService.findCategoryByName(specialCategory.getName());
        if(existed != null && existed.getId() != detail.getId()) {
            model.addAttribute("specialCategory", specialCategory);
            return "admin/specailcate/edit";
        }

        detail.setId(specialCategory.getId());
        detail.setName(specialCategory.getName());
        specailCategoryService.update(detail);
        return "redirect:/admin/specailcategories";
    }


    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) {
        model.addObject("specailcate", new SpecialCategory());
        model.setViewName("admin/specailcate/create");
        return model;
    }

    @PostMapping("/create")
    public String createSpecailCategory(Model model, @Valid SpecialCategory specailcate, BindingResult result) {

        SpecialCategory existingCategory = specailCategoryService.findCategoryByName(specailcate.getName());

        if (existingCategory != null && existingCategory.getName() != null
                && !existingCategory.getName().isEmpty()) {
            result.rejectValue("name", null,
                    "There is already an account registered with the same name");
        }
        if(result.hasErrors()) {
            model.addAttribute("specailcate",specailcate);
            return"admin/specailcate/create";
        }

        specailCategoryService.create(specailcate);

        return "redirect:/admin/specailcategories";
    }
}

