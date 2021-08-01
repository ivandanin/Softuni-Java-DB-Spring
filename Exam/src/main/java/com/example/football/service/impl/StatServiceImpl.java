package com.example.football.service.impl;

import com.example.football.models.dto.xmls.StatRootDto;
import com.example.football.models.dto.xmls.StatsDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StatServiceImpl implements StatService {

    private static final String STAT_PATH = "src/main/resources/files/xml/stats.xml" ;
    private final StatRepository statRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

    }

    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(STAT_PATH)));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        StatRootDto statRootDto = this.xmlParser.parseXml(StatRootDto.class, STAT_PATH);

        for (StatsDto statsDto : statRootDto.getStats()) {
            Stat stat = this.modelMapper.map(statsDto, Stat.class);
            if (this.validationUtil.isValid(stat)) {
                this.statRepository.saveAndFlush(stat);

                sb.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                        stat.getPassing(), stat.getShooting(), stat.getEndurance()))
                .append(System.lineSeparator());
            } else {
                sb.append("Invalid Stat")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
