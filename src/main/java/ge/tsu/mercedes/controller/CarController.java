package ge.tsu.mercedes.controller;

import ge.tsu.mercedes.model.Car;
import ge.tsu.mercedes.service.CarCategoryService;
import ge.tsu.mercedes.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarCategoryService categoryService;

    public CarController(CarService carService, CarCategoryService categoryService) {
        this.carService = carService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("car", new Car());
        addFormData(model);
        return "cars/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("car") Car car,
                         BindingResult bindingResult,
                         @RequestParam("image") MultipartFile image,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormData(model);
            return "cars/form";
        }

        carService.save(car, image);
        redirectAttributes.addFlashAttribute("successMessage", "მანქანა წარმატებით დაემატა");
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        addFormData(model);
        return "cars/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("car") Car car,
                         BindingResult bindingResult,
                         @RequestParam("image") MultipartFile image,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormData(model);
            return "cars/form";
        }

        Car existingCar = carService.findById(id);
        car.setId(existingCar.getId());
        if (car.getImageFileName() == null) {
            car.setImageFileName(existingCar.getImageFileName());
        }
        carService.save(car, image);
        redirectAttributes.addFlashAttribute("successMessage", "მანქანა წარმატებით განახლდა");
        return "redirect:/cars";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        carService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "მანქანა წაიშალა");
        return "redirect:/cars";
    }

    private void addFormData(Model model) {
        model.addAttribute("categories", categoryService.findAll());
    }
}
