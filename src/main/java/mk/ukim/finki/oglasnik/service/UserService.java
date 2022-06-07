package mk.ukim.finki.oglasnik.service;

import mk.ukim.finki.oglasnik.model.User;

public interface UserService {
    User create(String username,
                String password,
                String repeatPassword,
                String name,
                String lastname,
                String email,
                String phone);
    User promoteToAdmin(String username);


}
