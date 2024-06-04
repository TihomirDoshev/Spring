package bg.softuni.mobilele.models.dto;

import bg.softuni.mobilele.models.enums.EngineTypeEnum;

public record AddOfferDto (
        String description,
        EngineTypeEnum engineType
){
    public static AddOfferDto empty(){
        return new AddOfferDto(null,null);
    }
}
