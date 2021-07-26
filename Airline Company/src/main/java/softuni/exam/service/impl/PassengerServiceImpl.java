package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.Town;
import softuni.exam.models.dtos.PassengerDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final String PASSENGER_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final TownService townService;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PASSENGER_PATH)));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();

        PassengerDto[] passengerDtos = this.gson
                .fromJson(this.readPassengersFileContent(), PassengerDto[].class);

        for (PassengerDto passengerDto : passengerDtos) {
            if (this.validationUtil.isValid(passengerDto)) {
                Town town = this.townService.getTownByName(passengerDto.getTown());
                if (this.passengerRepository.findByEmail(passengerDto.getEmail()) == null) {
                    Passenger passenger = this.modelMapper.map(passengerDto, Passenger.class);
                    passenger.setTown(town);
                    this.passengerRepository.saveAndFlush(passenger);

                    sb.append(String.format("Successfully imported Passenger %s - %s",
                            passengerDto.getLastName(), passengerDto.getEmail()))
                    .append(System.lineSeparator());
                    
                } else {
                    sb.append("Invalid Passenger").append(System.lineSeparator());
                }
            } else {
                sb.append("Invalid Passenger").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return null;
    }
}
