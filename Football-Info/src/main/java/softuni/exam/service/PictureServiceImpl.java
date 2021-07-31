package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.dtos.xmls.PictureDto;
import softuni.exam.domain.entities.dtos.xmls.PictureRootDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PIC_PATH = "src/main/resources/files/xml/pictures.xml";
    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ValidatorUtil validatorUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }
    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return this.fileUtil.readFile(PIC_PATH);
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        PictureRootDto pictureRootDto = this.xmlParser.parseXml(PictureRootDto.class, PIC_PATH);

        for (PictureDto pictureDto : pictureRootDto.getPictures()) {
            Picture picture = this.modelMapper.map(pictureDto, Picture.class);

            if (this.validatorUtil.isValid(picture)) {
                this.pictureRepository.saveAndFlush(picture);
                sb.append(String.format("Successfully imported picture - %s",
                        picture.getUrl()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid picture")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }



}
