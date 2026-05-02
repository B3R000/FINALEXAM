package ge.tsu.mercedes.controller;

import ge.tsu.mercedes.config.ShowroomProperties;
import ge.tsu.mercedes.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarService carService;
    private final ShowroomProperties properties;

    public HomeController(CarService carService, ShowroomProperties properties) {
        this.carService = carService;
        this.properties = properties;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("showroomName", properties.getName());
        model.addAttribute("supportEmail", properties.getSupportEmail());
        model.addAttribute("cars", carService.findFeatured());
        return "index";
    }
}
