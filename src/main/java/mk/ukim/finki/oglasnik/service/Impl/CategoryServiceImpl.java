package mk.ukim.finki.oglasnik.service.Impl;

import mk.ukim.finki.oglasnik.model.Category;
import mk.ukim.finki.oglasnik.repository.CategoryRepository;
import mk.ukim.finki.oglasnik.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(category);
        categoryRepository.save(c);
        return c;
    }

//    @Override
//    public Category update(String category) {
//
//    }

    @Override
    public void delete(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByCategoryName(category);
    }

    @Override
    public void deleteById(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}
