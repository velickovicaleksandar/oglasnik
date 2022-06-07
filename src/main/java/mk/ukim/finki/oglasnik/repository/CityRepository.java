package mk.ukim.finki.oglasnik.repository;

import mk.ukim.finki.oglasnik.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
City findByCity(String city);
void deleteByCity(String city);

}
