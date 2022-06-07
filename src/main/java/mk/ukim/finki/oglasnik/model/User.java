package mk.ukim.finki.oglasnik.model;

import lombok.Data;
import mk.ukim.finki.oglasnik.model.enumeration.Role;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oglasnik_users")
@Data
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password, String name, String lastname, String email, String phone,Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;

    }
}
