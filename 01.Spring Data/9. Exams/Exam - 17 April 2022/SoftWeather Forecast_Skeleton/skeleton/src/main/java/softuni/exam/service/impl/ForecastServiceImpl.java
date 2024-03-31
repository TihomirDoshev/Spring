package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ForecastRootDto;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String FILE_PATH = "src/main/resources/files/xml/forecasts.xml";
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count()>0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(FILE_PATH, ForecastRootDto.class)
                .getForecastDto().stream()
                .filter(forecastDto -> {
                    boolean isValid = validationUtil.isValid(forecastDto)
                            && !ifSameForecastExist(forecastDto.getDayOfWeek(),forecastDto.getCity());
                    sb.append(isValid ? String.format("Successfully import forecast %s - %.2f",forecastDto.getDayOfWeek(),forecastDto.getMaxTemperature())
                            :"Invalid forecast");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(forecastDto -> {
                    Forecast forecast = modelMapper.map(forecastDto,Forecast.class);
                    forecast.setCity(cityRepository.findCityById(forecastDto.getCity()));
                    return forecast;
                })
                .forEach(forecastRepository::save);
        return sb.toString();
    }

    private boolean ifSameForecastExist(DayOfWeek dayOfWeek, Long city) {
        return forecastRepository.existsByIdAndDayOfWeek(city,dayOfWeek);
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb  = new StringBuilder();
        forecastRepository.findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY,150000)
                .forEach(f ->{
                    sb.append(String.format("City: %s:\n" +
                            "   -min temperature: %.2f\n" +
                            "   --max temperature: %.2f\n" +
                            "   ---sunrise: %s\n" +
                            "   ----sunset: %s\n",f.getCity().getCityName(),f.getMinTemperature(),
                            f.getMaxTemperature(),f.getSunrise(),f.getSunset()));
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
