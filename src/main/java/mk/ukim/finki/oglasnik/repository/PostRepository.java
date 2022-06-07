package mk.ukim.finki.oglasnik.repository;

import mk.ukim.finki.oglasnik.model.Category;
import mk.ukim.finki.oglasnik.model.City;
import mk.ukim.finki.oglasnik.model.Post;
import mk.ukim.finki.oglasnik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
List<Post> findAllByCategory(Category category);
List<Post> findAllByCity(City city);
List<Post> findAllByCategoryAndCityAndTitleContaining(Category category,City city,String title);
List<Post> findAllByCategoryAndCity(Category category,City city);
List<Post> findAllByCityAndTitleContaining(City city,String title);
List<Post> findAllByCategoryAndTitleContaining(Category category,String title);
List<Post> findAllByUser(User u);

List<Post> findByTitleIsContainingOrDescriptionContaining(String s1, String s2);
List<Post> findByTitleIsContainingOrDescriptionIsContainingAndCategoryAndCity(String title,String description,Category category, City city);

}
