package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CityDto;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CityServiceImpl implements CityService {
    private static final String FILE_PATH = "src/main/resources/files/json/cities.json";

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return cityRepository.count()>0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readCitiesFileContent(), CityDto[].class))
                .filter(cityDto -> {
                    boolean isValid = validationUtil.isValid(cityDto)
                            && !isCityExist(cityDto.getCityName());
                    sb.append(isValid ? String.format("Successfully imported city %s - %d",cityDto.getCityName(),cityDto.getPopulation())
                            :"Invalid city");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(cityDto -> {
                    City city = modelMapper.map(cityDto,City.class);
                    city.setCountry(countryRepository.findCountryById(cityDto.getCountry()));
                    return city;
                })
                .forEach(cityRepository::save);
        return sb.toString();
    }

    private boolean isCityExist(String cityName) {
        return cityRepository.existsByCityName(cityName);
    }
}
