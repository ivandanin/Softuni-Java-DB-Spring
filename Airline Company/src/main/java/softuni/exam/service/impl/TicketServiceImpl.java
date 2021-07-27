package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Ticket;
import softuni.exam.models.Town;
import softuni.exam.models.dtos.xmls.TicketDto;
import softuni.exam.models.dtos.xmls.TicketRootDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String TICKET_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TICKET_PATH)));
    }

    @Override
    public String importTickets() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        TicketRootDto ticketRootDtos = this.xmlParser.parseXml(TicketRootDto.class, TICKET_PATH);

        for (TicketDto ticketDto : ticketRootDtos.getTickets()) {
            if (this.validationUtil.isValid(ticketDto)) {
                Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);

                ticket.setTakeoff(LocalDateTime.parse(ticketDto.getTakeoff(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                ticket.setFromTown(this.townRepository.findByName(ticketDto.getFromTown().getName()));
                ticket.setToTown(this.townRepository.findByName(ticketDto.getToTown().getName()));
                ticket.setPassenger(this.passengerRepository.findByEmail(ticketDto.getPassengerDto().getEmail()));
                ticket.setPlane(this.planeRepository.findByRegisterNumber(ticket.getPlane().getRegisterNumber()));

                this.ticketRepository.saveAndFlush(ticket);

                sb.append(String.format("Successfully imported Ticket %s - %s",
                        ticketDto.getFromTown().getName(), ticketDto.getToTown().getName()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid Ticket")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
