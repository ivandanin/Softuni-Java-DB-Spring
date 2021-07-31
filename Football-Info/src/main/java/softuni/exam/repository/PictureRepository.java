package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.dtos.xmls.PictureDto;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Picture findByUrl(String url);
}
