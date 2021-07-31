package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.domain.entities.dtos.xmls.TeamDto;
import softuni.exam.domain.entities.dtos.xmls.TeamRootDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAM_PATH = "src/main/resources/files/xml/teams.xml";
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final PictureRepository pictureRepository;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, XmlParser xmlParser, FileUtil fileUtil, ValidatorUtil validatorUtil, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return this.fileUtil.readFile(TEAM_PATH);
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        TeamRootDto teamRootDto = this.xmlParser.parseXml(TeamRootDto.class, TEAM_PATH);

        for (TeamDto teamDto : teamRootDto.getTeams()) {
            Team team = this.modelMapper.map(teamDto, Team.class);
            Picture picture = this.pictureRepository
                    .findByUrl(teamDto.getPictureDto().getUrl());

            team.setPicture(picture);

            if (this.validatorUtil.isValid(team)) {
                this.teamRepository.saveAndFlush(team);

                sb.append(String.format("Successfully imported - %s",
                        team.getName()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid team")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

}
