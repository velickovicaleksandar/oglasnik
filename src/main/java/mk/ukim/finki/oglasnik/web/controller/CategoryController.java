package mk.ukim.finki.oglasnik.web.controller;

import mk.ukim.finki.oglasnik.model.Category;
import mk.ukim.finki.oglasnik.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public String allCategories(Model model){
        List<Category> categories = this.categoryService.listAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }
    @PostMapping
    public String addCategory(@RequestParam String category){
        this.categoryService.create(category);
        return "redirect:/posts";
    }
    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }
}
