package mk.ukim.finki.oglasnik.service.Impl;

import mk.ukim.finki.oglasnik.model.Category;
import mk.ukim.finki.oglasnik.model.City;
import mk.ukim.finki.oglasnik.model.Post;
import mk.ukim.finki.oglasnik.model.User;
import mk.ukim.finki.oglasnik.model.exceptions.CityNotFoundException;
import mk.ukim.finki.oglasnik.model.exceptions.PostNotFoundException;
import mk.ukim.finki.oglasnik.model.exceptions.UserNotFoundException;
import mk.ukim.finki.oglasnik.repository.CategoryRepository;
import mk.ukim.finki.oglasnik.repository.CityRepository;
import mk.ukim.finki.oglasnik.repository.PostRepository;
import mk.ukim.finki.oglasnik.repository.UserRepository;
import mk.ukim.finki.oglasnik.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, CityRepository cityRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public List<Post> findByCategory(String category) {
        Category c = categoryRepository.findByCategoryName(category);
        return postRepository.findAllByCategory(c);
    }

    @Override
    public Post save(Long categoryId, Long cityId,String title, String image, String description, String contactMail, String contactNumber, String username) {
        Category c = this.categoryRepository.findById(categoryId).orElseThrow(() -> new PostNotFoundException(categoryId));
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));
        User u = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        Post p = new Post(c, city, title,image, description, contactMail, contactNumber, u);
        this.postRepository.save(p);
        return p;

    }

    @Override
    public Post edit(Long postId, Long categoryId, Long cityId, String title, String image, String description, String contactMail, String contactNumber) {
        Post p = this.postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        Category c = this.categoryRepository.findById(categoryId).orElseThrow(() -> new PostNotFoundException(categoryId));
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));
        p.setCategory(c);
        p.setCity(city);
        p.setTitle(title);

        p.setImage(image);
        p.setDescription(description);
        p.setContactMail(contactMail);
        p.setContactNumber(contactNumber);

        return this.postRepository.save(p);

    }

    @Override
    public List<Post> findByCity(String city) {
        City c = this.cityRepository.findByCity(city);
        return this.postRepository.findAllByCity(c);
    }

    @Override
    public List<Post> findByCategoryAndCity(String category, String city) {
        List<Post> postList = new ArrayList<>();
        if (category != null && !category.isEmpty() && city != null && !city.isEmpty()) {
            City c = this.cityRepository.findByCity(city);
            Category category1 = this.categoryRepository.findByCategoryName(category);
            postList = this.postRepository.findAllByCategoryAndCity(category1, c);
        } else if (!category.isEmpty() && category != null) {
//            Category category1 = this.categoryRepository.findByCategoryName(category);
//            return this.postRepository.findAllByCategory(category1);
            postList = findByCategory(category);
        } else if (city != null && !city.isEmpty()) {
//            City c = this.cityRepository.findByCity(city);
//            return this.postRepository.findAllByCity(c);
            postList = findByCity(city);
        }
        return postList;
    }

    @Override
    public List<Post> findByUsername(String username) {
        User u = this.userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        return this.postRepository.findAllByUser(u);
    }

    @Override
    public List<Post> searchTitleAndDescriptionOrCategoryOrCity(String search, Long category, Long city) {
        City city1;
        Category cat;
        List<Post> posts = new ArrayList<Post>();

        if(city!= null)
           city1 = this.cityRepository.findById(city).orElse(null);
        else
            city1 = null;
        if(category!=null)
            cat = this.categoryRepository.findById(category).orElse(null);
        else
            cat=null;
        if(cat == null && city == null && !search.isEmpty())
            posts= this.postRepository.findByTitleIsContainingOrDescriptionContaining(search,search);
        else if(cat == null && city!= null && search.isEmpty())
            posts = this.postRepository.findAllByCity(city1);
        else if(cat != null && city== null && search.isEmpty())
            posts = this.postRepository.findAllByCategory(cat);
        else if(cat == null && city != null && !search.isEmpty())
            posts= this.postRepository.findAllByCityAndTitleContaining(city1,search);
        else if(cat!=null && city !=null && search.isEmpty())
            posts= this.postRepository.findAllByCategoryAndCity(cat,city1);
       else if(cat!=null && city!= null && !search.isEmpty())
            posts= this.postRepository.findAllByCategoryAndCityAndTitleContaining(cat,city1,search);
        return posts;
    }





    //return this.postRepository.findByTitleIsContainingOrDescriptionIsContainingAndCategoryAndCity(search,search,cat,city1);

    }


