package mk.ukim.finki.oglasnik.service;

import mk.ukim.finki.oglasnik.model.User;

public interface AuthService {
    User login(String username, String password);
}
