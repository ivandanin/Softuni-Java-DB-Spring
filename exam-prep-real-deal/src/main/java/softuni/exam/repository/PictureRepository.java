package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.Picture;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Optional<Picture> findByName(String name);
}
