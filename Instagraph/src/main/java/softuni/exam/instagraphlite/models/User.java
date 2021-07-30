package softuni.exam.instagraphlite.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private Integer id;
    private String username;
    private String password;
    private Picture profilePicture;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    @Length(min = 2, max = 18)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 4)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "profile_picture")
    @ManyToOne
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }
}
