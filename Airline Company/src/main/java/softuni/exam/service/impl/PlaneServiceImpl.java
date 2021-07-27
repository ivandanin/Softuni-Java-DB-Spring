package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Plane;
import softuni.exam.models.dtos.xmls.PlaneDto;
import softuni.exam.models.dtos.xmls.PlaneRootDto;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {

    private static final String PLANE_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PLANE_PATH)));
    }

    @Override
    public String importPlanes() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        PlaneRootDto planeRootDto = this.xmlParser.parseXml(PlaneRootDto.class, PLANE_PATH);

        for (PlaneDto planeDto : planeRootDto.getPlanes()) {
            if (this.validationUtil.isValid(planeDto)) {
                Plane plane = this.modelMapper.map(planeDto, Plane.class);

                this.planeRepository.saveAndFlush(plane);
                sb.append(String.format("Successfully imported Plane %s",
                        planeDto.getRegisterNumber()))
                        .append(System.lineSeparator());

            } else {
                sb.append("Invalid Plane");
            }
        }
        return sb.toString();
    }

    @Override
    public Plane findByRegisterNumber(String registerNumber) {
        return this.planeRepository.findByRegisterNumber(registerNumber);
    }


}
