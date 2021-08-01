package com.example.football.service.impl;

import com.example.football.models.dto.jsons.TeamDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAM_PATH = "src/main/resources/files/json/teams.json";
    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TeamServiceImpl(TeamRepository teamRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TEAM_PATH)));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();

        TeamDto[] teamDtos = this.gson.fromJson(this.readTeamsFileContent(), TeamDto[].class);

        for (TeamDto teamDto : teamDtos) {
            Team team = this.modelMapper.map(teamDto, Team.class);

            if (this.validationUtil.isValid(team)) {
                this.teamRepository.saveAndFlush(team);

                sb.append(String.format("Successfully imported Team %s - %d",
                        team.getName(), team.getFanBase()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid Team")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
