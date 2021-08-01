package com.example.football.service.impl;

import com.example.football.models.dto.xmls.PlayerDto;
import com.example.football.models.dto.xmls.PlayerRootDto;
import com.example.football.models.entity.*;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYER_PATH = "src/main/resources/files/xml/players.xml";
    private final PlayerRepository playerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PLAYER_PATH)));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        PlayerRootDto playerRootDto = this.xmlParser.parseXml(PlayerRootDto.class, PLAYER_PATH);

        for (PlayerDto playerDto : playerRootDto.getPlayers()) {

            Player player = this.modelMapper.map(playerDto, Player.class);
            Position position = playerDto.getPosition();
            Town town = this.modelMapper.map(playerDto.getTown(), Town.class);
            Team teamFromDto = this.modelMapper.map(playerDto.getTeam(), Team.class);
            Team team = this.teamRepository.findByName(teamFromDto.getName());
            Stat stat = this.modelMapper.map(playerDto.getStat(), Stat.class);

            player.setPosition(position);
            player.setTown(town);
            player.setTeam(team);
            player.setStat(stat);

            if (this.validationUtil.isValid(player)) {
                this.playerRepository.saveAndFlush(player);

                sb.append(String.format("Successfully imported Player %s %s - %s",
                        player.getFirstName(), player.getLastName(), player.getPosition()))
                        .append(System.lineSeparator());
            } else {
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        List<Player> players = this.playerRepository.exportBestPlayers();

        for (Player player : players) {
            sb.append(String.format("Player - %s %s%n" +
                            "\tPosition - %s%n" +
                            "\tTeam - %s%n" +
                            "\tStadium - %s",
                    player.getFirstName(), player.getLastName(),
                    player.getPosition(), player.getTeam().getName(),
                    player.getTeam().getStadiumName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
