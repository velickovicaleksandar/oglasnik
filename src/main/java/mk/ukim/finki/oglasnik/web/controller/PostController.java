package mk.ukim.finki.oglasnik.web.controller;

import mk.ukim.finki.oglasnik.model.Category;
import mk.ukim.finki.oglasnik.model.City;
import mk.ukim.finki.oglasnik.model.Post;
import mk.ukim.finki.oglasnik.service.CategoryService;
import mk.ukim.finki.oglasnik.service.CityService;
import mk.ukim.finki.oglasnik.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/","/posts"})
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

    public PostController(PostService postService, CategoryService categoryService, CityService cityService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.cityService = cityService;
    }
    @GetMapping
    public String listAllPosts(Model model){
        List<Post> posts = this.postService.listAllPosts();
        List<City> cities = this.cityService.listAllCities();
        List<Category> categories = this.categoryService.listAllCategories();
        model.addAttribute("posts",posts);
        model.addAttribute("categories",categories);
        model.addAttribute("cities",cities);
        model.addAttribute("bodyContent","posts");
        return "master-template";
    }
    @GetMapping("/{id}")
    public String seePost(@PathVariable Long id, Model model){
        Post p = this.postService.findById(id);
        model.addAttribute("post",p);
        model.addAttribute("bodyContent","post");
        return "master-template";
    }
    @PostMapping(value = "/{id}/delete")
    public String deletePost(@PathVariable Long id){
        this.postService.deleteById(id);
        return "redirect:/posts";
    }
    @GetMapping("/add")
    public String addPost(Model model){
        List<City> cities = this.cityService.listAllCities();
        List<Category> categories = this.categoryService.listAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("cities",cities);
        model.addAttribute("bodyContent", "add-post");
       return "master-template";

    }
    @PostMapping("/add")
    public String addPost(@RequestParam(required = false) Long id,
                          @RequestParam Long category, @RequestParam Long city,
                          @RequestParam String title,
                          @RequestParam String image,
                          @RequestParam String description,
                          @RequestParam String contactMail,
                          @RequestParam String contactNumber,
                          @RequestParam String username){
        if(id!=null){
            this.postService.edit(id,category,city,title,image,description,contactMail,contactNumber);
        }
        else {
            this.postService.save(category, city, title, image, description, contactMail, contactNumber, username);
        }
        return "redirect:/posts";
    }
    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, Model model){
        Post p = this.postService.findById(id);

        List<City> cities = this.cityService.listAllCities();
        List<Category> categories = this.categoryService.listAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("cities",cities);
        model.addAttribute("p",p);
        model.addAttribute("bodyContent", "add-post");
        return "master-template";


    }
    @PostMapping("/edit-post")
    public String editPost(@RequestParam(required = false) Long Id,@RequestParam Long category, @RequestParam Long city,
                          @RequestParam String title,
                          @RequestParam String image,
                          @RequestParam String description,
                          @RequestParam String contactMail,
                          @RequestParam String contactNumber
                         ){
        this.postService.edit(Id,category,city,title,image,description,contactMail,contactNumber);
        return "redirect:/posts";
    }
    @GetMapping("/{username}/posted")
    public String postsByUser(@PathVariable String username, Model model){
        List<Post> posts = this.postService.findByUsername(username);
        model.addAttribute("posts",posts);
        model.addAttribute("bodyContent","posts");
        return "master-template";
    }
    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam(required = false) String search,@RequestParam(required = false)Long city,@RequestParam(required = false) Long category, Model model){
        if(search.equals("") && city == null && category == null){ return "redirect:/posts";}
        List<Post> posts = this.postService.searchTitleAndDescriptionOrCategoryOrCity(search,category,city);
        model.addAttribute("posts",posts);
        model.addAttribute("bodyContent","posts");
        return "master-template";
    }
}

