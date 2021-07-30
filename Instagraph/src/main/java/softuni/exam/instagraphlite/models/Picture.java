package softuni.exam.instagraphlite.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pictures")
public class Picture {

    private Integer id;
    private String path;
    private Float size;

    public Picture() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NotNull
    @Size(min = 500, max = 60000)
    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }
}
