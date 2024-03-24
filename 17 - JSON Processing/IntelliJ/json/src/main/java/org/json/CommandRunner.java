package org.json;

import com.google.gson.Gson;
import org.json.dto.AddressDTO;
import org.json.dto.PersonDTO;
import org.json.services.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {
    private Gson gson;
    private PersonService personService;

    public CommandRunner(@Qualifier("withNulls") Gson gson, PersonService personService) {
        this.gson = gson;
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
//        Gson gson = new GsonBuilder()
//                //.excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .setPrettyPrinting()
//                .create();


        // JSON TO JSON
        jsonToJson(gson);
        
        
        //JSON FROM JSON
        jsonFromJson(gson);
    }

    private void jsonFromJson(Gson gson) {
        String gson1  = """
                [
                    {
                    "firstName": "Pesho",
                    "lastName": "Peshov",
                    "age": 24,
                    "isMarried": true,
                    "lotteryNumbers": [
                      1,
                      2,
                      5,
                      22,
                      45
                    ],
                    "addressDTO": {
                      "country": "Bg",
                      "city": "Burgas"
                    }
                    },
                    {
                    "firstName": "Ignat",
                    "lastName": "Petrov",
                    "age": 54,
                    "isMarried": true,
                    "lotteryNumbers": [
                      
                      22,
                      45
                    ],
                    "addressDTO": {
                      "country": "Bg",
                      "city": "Sofia"
                    }
                    }
                ]    
                """;
        PersonDTO[] personDTO = gson.fromJson(gson1, PersonDTO[].class);
        for (PersonDTO dto : personDTO) {
            System.out.println(dto);

        }
    }

    private void jsonToJson(Gson gson) {
        PersonDTO personDTO =  new PersonDTO("Pesho"
                ,"Peshov"
                ,24
                ,true
                , List.of(1,2,5,22,45)
                ,new AddressDTO("Bg","Burgas"));

        //           JSON TO JASON

        //String json = gson.toJson(personDTO);
        String json = gson.toJson(List.of(personDTO,personDTO));
        System.out.println(json);

    }
}
