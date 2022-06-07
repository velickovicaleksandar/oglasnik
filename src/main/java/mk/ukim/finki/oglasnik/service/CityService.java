package mk.ukim.finki.oglasnik.service;

import mk.ukim.finki.oglasnik.model.City;

import java.util.List;

public interface CityService {
    List<City> listAllCities();
    City addCity(String city);
    void deleteCity(String city);
    void deleteCityById(Long id);
}
