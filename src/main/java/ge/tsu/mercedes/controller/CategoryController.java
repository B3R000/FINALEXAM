package ge.tsu.mercedes.controller;

import ge.tsu.mercedes.model.CarCategory;
import ge.tsu.mercedes.service.CarCategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CarCategoryService categoryService;

    public CategoryController(CarCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", new CarCategory());
        return "categories/list";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("category") CarCategory category,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "categories/list";
        }

        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "კატეგორია დაემატა");
        return "redirect:/categories";
    }
}
