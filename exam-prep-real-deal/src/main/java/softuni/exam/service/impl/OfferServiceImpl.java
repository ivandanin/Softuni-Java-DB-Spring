package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.Seller;
import softuni.exam.models.dtos.xmls.OfferDto;
import softuni.exam.models.dtos.xmls.OfferRootDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFERS_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferRootDto offerRootDto = this.xmlParser.parseXml(OfferRootDto.class, OFFERS_PATH);

        for (OfferDto offerDto : offerRootDto.getOfferDtos()) {
            if (this.validationUtil.isValid(offerDto)) {
                Offer offer = this.modelMapper.map(offerDto, Offer.class);

                Car car = this.carRepository.findById(offerDto.getCar().getId()).get();
                Seller seller = this.sellerRepository.findById(offerDto.getSeller().getId()).get();

                offer.setPictures(new HashSet<>(car.getPictures()));
                offer.setCar(car);
                offer.setSeller(seller);

                this.offerRepository.saveAndFlush(offer);
                sb.append(String.format("Successfully import offer %s - %s",
                        offer.getAddedOn(), offer.isHasGoldStatus()))
                        .append(System.lineSeparator());

            } else {
                sb.append("Invalid seller")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
