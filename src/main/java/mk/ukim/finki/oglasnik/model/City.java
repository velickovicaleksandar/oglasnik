package mk.ukim.finki.oglasnik.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String city;
    @OneToMany(mappedBy = "city")
    List<Post> posts;

    public City() {
    }

    public City(String city) {
        this.city = city;
    }

    @PreRemove
    public void nullifyPostCity(){
        posts.forEach(p->p.setCity(null));
    }
}
