package mk.ukim.finki.oglasnik.service;

import mk.ukim.finki.oglasnik.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String category);
   // Category update(String category);
    void delete(String category);
    void deleteById(Long categoryId);
    List<Category> listAllCategories();


}
