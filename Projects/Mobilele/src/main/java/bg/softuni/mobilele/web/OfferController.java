package bg.softuni.mobilele.web;
import bg.softuni.mobilele.models.dto.AddOfferDto;
import bg.softuni.mobilele.models.enums.EngineTypeEnum;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/offers/add")
    public String newOffer(Model model){
        if (!model.containsAttribute("addOfferDto")){
            model.addAttribute("addOfferDto",AddOfferDto.empty());

        }
        model.addAttribute("allEngineTypes",EngineTypeEnum.values());

        return "offer-add";
    }
    @PostMapping("/offers/add")
    public String createOffer(AddOfferDto addOfferDto){
        offerService.createOffer(addOfferDto);

        return "offer-add";
    }
}
