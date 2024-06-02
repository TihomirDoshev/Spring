package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.VolcanoSeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private static final String FILE_PATH = "src/main/resources/files/json/volcanoes.json";
    private final VolcanoRepository volcanoRepository;
    private final CountryRepository countryRepository;

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return volcanoRepository.count()>0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readVolcanoesFileContent(), VolcanoSeedDto[].class))
                .filter(volcanoSeedDto -> {
                    boolean isValid = validationUtil.isValid(volcanoSeedDto)
                            && !isVolcanoExist(volcanoSeedDto.getName());
                    sb.append(isValid ? String.format("Successfully imported volcano %s of type %s",volcanoSeedDto.getName(),volcanoSeedDto.getVolcanoType())
                            :"Invalid volcano");
                    sb.append(System.lineSeparator());
                    return isValid;

                })
                .map(volcanoSeedDto -> {
                    Volcano volcano = modelMapper.map(volcanoSeedDto,Volcano.class);
                    volcano.setCountry(findCountry(volcanoSeedDto.getCountry()));
                    return volcano;
                })
                .forEach(volcanoRepository::save);

        return sb.toString();
    }

    private Country findCountry(long country) {
        return countryRepository.findCountryById(country);
    }

    private boolean isVolcanoExist(String name) {
        return volcanoRepository.existsVolcanoByName(name);
    }

    @Override
    public String exportVolcanoes() {
        StringBuilder sb = new StringBuilder();
        volcanoRepository.findAllByActiveAndElevationMoreThanAndLastEruptionIsNotNullOrderByElevationDesc()
                .forEach(v ->{
                    sb.append(String.format("Volcano: %s\n" +
                            "   *Located in: %s\n" +
                            "   **Elevation: %d\n" +
                            "   ***Last eruption on: %s\n",v.getName(),v.getCountry().getName(),v.getElevation(),v.getLastEruption()));

                });
        return sb.toString();
    }
}