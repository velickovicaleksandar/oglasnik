package mk.ukim.finki.oglasnik.web.controller;

import mk.ukim.finki.oglasnik.model.City;
import mk.ukim.finki.oglasnik.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping
    public String allCities(Model model){
        List<City> cities = this.cityService.listAllCities();
        model.addAttribute("cities",cities);
        model.addAttribute("bodyContent","cities");

        return "master-template";
    }
    @PostMapping
    public String addCity(@RequestParam String city){
        this.cityService.addCity(city);
        return "redirect:/posts";
    }
    @PostMapping("/{id}/delete")
    public String deleteCity(@PathVariable Long id){
        this.cityService.deleteCityById(id);
    return "redirect:/admin/cities";
    }

}
