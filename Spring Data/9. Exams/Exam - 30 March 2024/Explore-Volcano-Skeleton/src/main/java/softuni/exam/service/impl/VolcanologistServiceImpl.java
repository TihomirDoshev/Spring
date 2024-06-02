package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.VolcanologistsSeedRootDto;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private static final String FILE_PATH = "src/main/resources/files/xml/volcanologists.xml";
    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return volcanologistRepository.count()>0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();


        xmlParser.fromFile(FILE_PATH, VolcanologistsSeedRootDto.class)
                .getVolcanologistsSeedDtoSet()
                .stream()
                .filter(volcanologistsSeedDto -> {
                    boolean isValid = validationUtil.isValid(volcanologistsSeedDto)
                            && !existVolcanologist(volcanologistsSeedDto.getFirstName(),volcanologistsSeedDto.getLastName())
                           && existVolcano(volcanologistsSeedDto.getVolcanoId());
                    sb.append(isValid ? String.format("Successfully imported volcanologist %s %s",volcanologistsSeedDto.getFirstName(),volcanologistsSeedDto.getLastName())
                            :"Invalid volcanologist");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(volcanologistsSeedDto -> {
                    Volcanologist volcanologist = modelMapper.map(volcanologistsSeedDto,Volcanologist.class);
                    volcanologist.setVolcano(volcanoRepository.findVolcanoById(volcanologistsSeedDto.getVolcanoId()));
                    return volcanologist;
                })
                .forEach(volcanologistRepository::save);
        return sb.toString();
    }

    private boolean existVolcano(long volcanoId) {
        return volcanoRepository.existsVolcanoById(volcanoId);
    }


    private boolean existVolcanologist(String firstName, String lastName) {
        return volcanologistRepository.existsByFirstNameAndLastName(firstName,lastName);
    }
}