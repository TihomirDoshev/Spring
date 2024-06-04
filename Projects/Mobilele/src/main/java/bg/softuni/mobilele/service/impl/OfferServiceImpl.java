package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.models.dto.AddOfferDto;
import bg.softuni.mobilele.models.entity.OfferEntity;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void createOffer(AddOfferDto addOfferDto) {
        offerRepository.save(map(addOfferDto));

    }
    private static OfferEntity map(AddOfferDto addOfferDto){
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setDescription(addOfferDto.description());
        offerEntity.setEngine(addOfferDto.engineType());
        return offerEntity;

    }
}
