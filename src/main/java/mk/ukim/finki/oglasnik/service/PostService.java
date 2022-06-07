package mk.ukim.finki.oglasnik.service;


import mk.ukim.finki.oglasnik.model.Post;

import java.util.List;

public interface PostService {
    List<Post> listAllPosts();
    void deleteById(Long id);
    Post findById(Long id);
    List<Post> findByCategory(String category);
    Post save( Long categoryId,
               Long cityId,
               String title,
               String image,
               String description,
               String contactMail,
               String contactNumber,
               String username);
    Post edit(Long postId,
              Long categoryId,
              Long cityId,
              String title,
              String image,
              String description,
              String contactMail,
              String contactNumber);
    List<Post> findByCity(String city);
    List<Post> findByCategoryAndCity(String category,String city);
    List<Post> findByUsername(String username);


    List<Post> searchTitleAndDescriptionOrCategoryOrCity(String search,Long category,Long city);
}
