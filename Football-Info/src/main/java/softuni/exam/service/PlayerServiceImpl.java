package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Position;
import softuni.exam.domain.entities.Team;
import softuni.exam.domain.entities.dtos.jsons.PlayerDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYER_PATH = "src/main/resources/files/json/players.json";
    private final PlayerRepository playerRepository;
    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, Gson gson, FileUtil fileUtil, ValidatorUtil validatorUtil, ModelMapper modelMapper, PictureRepository pictureRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return this.fileUtil.readFile(PLAYER_PATH);
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();

        PlayerDto[] playerDtos = this.gson.fromJson(readPlayersJsonFile(), PlayerDto[].class);

        for (PlayerDto playerDto : playerDtos) {
            Player player = this.modelMapper.map(playerDto, Player.class);
            Picture pictureDto = this.modelMapper.map(playerDto.getPicture(), Picture.class);
            Picture picture = this.pictureRepository.findByUrl(pictureDto.getUrl());
            Team teamDto = this.modelMapper.map(player.getTeam(), Team.class);
            Team team = this.teamRepository.findByName(teamDto.getName());
            Position position = playerDto.getPosition();

            player.setPosition(position);
            player.setPicture(picture);
            player.setTeam(team);

            if (this.validatorUtil.isValid(player)) {
                this.playerRepository.saveAndFlush(player);

                sb.append(String.format("Successfully imported player - %s %s",
                        player.getFirstName(), player.getLastName()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid player")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();
        List<Player> players = this.playerRepository.exportPlayersWhereSalaryBiggerThan();

        for (Player player : players) {
            sb.append(String.format("Player name: %s %s",
                    player.getFirstName(), player.getLastName()))
                    .append(System.lineSeparator())

                    .append(String.format("\tNumber %d",
                            player.getNumber()))
                    .append(System.lineSeparator())

                    .append(String.format("\tSalary %s",
                            player.getSalary()))
                    .append(System.lineSeparator());

            Team team = this.teamRepository
                    .findById(player.getTeam().getId()).orElse(null);
            sb.append(String.format("\tTeam name: %s",
                    team.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        List<Player> players = this.playerRepository.exportPlayersInATeam();

        sb.append("Team: North Hub")
                .append(System.lineSeparator());

        for (Player player : players) {
            sb.append(String.format("\tPlayer name: %s %s",
                    player.getFirstName(), player.getLastName()))
                    .append(System.lineSeparator())

                    .append(String.format("\tNumber: %d",
                            player.getNumber()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
