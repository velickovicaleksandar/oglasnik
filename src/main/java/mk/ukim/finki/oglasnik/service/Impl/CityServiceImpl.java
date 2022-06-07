package mk.ukim.finki.oglasnik.service.Impl;

import mk.ukim.finki.oglasnik.model.City;
import mk.ukim.finki.oglasnik.repository.CityRepository;
import mk.ukim.finki.oglasnik.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> listAllCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public City addCity(String city) {
       City c = new City(city);
       this.cityRepository.save(c);
       return c;
    }

    @Override
    public void deleteCity(String city) {
        this.cityRepository.deleteByCity(city);
    }

    @Override
    public void deleteCityById(Long id) {
        this.cityRepository.deleteById(id);
    }


}
