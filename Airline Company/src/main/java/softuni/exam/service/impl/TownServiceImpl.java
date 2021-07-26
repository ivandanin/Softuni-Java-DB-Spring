package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Town;
import softuni.exam.models.dtos.TownDto;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWN_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readString(Path.of(TOWN_PATH)));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        TownDto[] townDtos = this.gson.fromJson(this.readTownsFileContent(), TownDto[].class);

        for (TownDto townDto : townDtos) {
            if (this.validationUtil.isValid(townDto)) {
                    this.townRepository.saveAndFlush(this.modelMapper.map(townDto, Town.class));

                    sb.append(String.format("Successfully imported Town %s - %d",
                            townDto.getName(), townDto.getPopulation()))
                            .append(System.lineSeparator());
            } else {
                sb.append("Invalid Town")
                        .append(System.lineSeparator());

            }
        }
        return sb.toString();
    }

    @Override
    public Town getTownByName(String town) {
        return this.townRepository.findByName(town);
    }
}
