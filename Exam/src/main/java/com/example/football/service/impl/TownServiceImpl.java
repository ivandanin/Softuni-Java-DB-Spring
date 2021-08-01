package com.example.football.service.impl;

import com.example.football.models.dto.jsons.TownDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TOWN_PATH)));
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
}
