package mk.ukim.finki.oglasnik.repository;

import mk.ukim.finki.oglasnik.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
void deleteByCategoryName(String categoryName);

Category findByCategoryName(String categoryName);
}
