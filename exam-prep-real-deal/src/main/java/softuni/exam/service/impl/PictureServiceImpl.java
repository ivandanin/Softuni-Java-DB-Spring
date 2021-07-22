package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Picture;
import softuni.exam.models.dtos.jsons.PictureDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_PATH = "src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURE_PATH)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureDto[] pictureDtos = this.gson.fromJson(this.readPicturesFromFile(), PictureDto[].class);
        for (PictureDto pictureDto : pictureDtos) {
            Optional<Picture> byName = this.pictureRepository.findByName(pictureDto.getName());

            if (this.validationUtil.isValid(pictureDto) && byName.isEmpty()) {

                Picture picture = this.modelMapper.map(pictureDto, Picture.class);
                picture.setCar(this.carRepository.getOne(pictureDto.getCar()));
                this.pictureRepository.saveAndFlush(picture);

                sb.append(String.format("Successfully imported picture - %s",
                        pictureDto.getName()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid picture")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
