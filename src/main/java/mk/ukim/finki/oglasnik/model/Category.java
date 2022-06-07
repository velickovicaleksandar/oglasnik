package mk.ukim.finki.oglasnik.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Post> posts;
    public Category() {
    }

    public Category( String categoryName) {

        this.categoryName = categoryName;
    }
    @PreRemove
    public void nullifyPostCategory(){
        posts.forEach(p ->p.setCategory(null));
    }
}
