package mk.ukim.finki.oglasnik.model;

import lombok.Data;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Category category;
    @ManyToOne
    private City city;
    private String title;
    private String image;

    @Column(length = 600)
    private String description;
    private String contactMail;
    private String contactNumber;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(Category category, City city, String title,String image, String description, String contactMail, String contactNumber, User user) {
        this.category = category;
        this.city = city;
        this.image = image;
        this.title =title;
        this.description = description;
        this.contactMail = contactMail;
        this.contactNumber = contactNumber;
        this.user = user;
    }
}
