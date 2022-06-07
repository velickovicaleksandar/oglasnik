package mk.ukim.finki.oglasnik.service.Impl;

import mk.ukim.finki.oglasnik.model.User;
import mk.ukim.finki.oglasnik.model.enumeration.Role;
import mk.ukim.finki.oglasnik.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.oglasnik.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.oglasnik.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.oglasnik.repository.UserRepository;
import mk.ukim.finki.oglasnik.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(String username, String password, String  repeatPassword,String name, String lastname, String email, String phone) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        String encryptedPassword = this.passwordEncoder.encode(password);
        User u = new User(username,encryptedPassword,name, lastname,email,phone, Role.ROLE_USER);
        this.userRepository.save(u);
        return u;
    }

    @Override
    public User promoteToAdmin(String username) {
        User u = this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        u.setRole(Role.ROLE_ADMIN);
        return this.userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User u = this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
      UserDetails userDetails = new org.springframework.security.core.userdetails.User(u.getUsername(),
              u.getPassword(),
              Stream.of( new SimpleGrantedAuthority(u.getRole().toString())).collect(Collectors.toList()));
      return userDetails;
    }
   @PostConstruct
   public void addFirstUser(){
       User user = new User("admin",this.passwordEncoder.encode("admin123"), "admin", "admin", "admin@admin.com", "075223305",Role.ROLE_ADMIN);
       userRepository.save(user);
   }
}
