package com.example.football.service.impl;

import com.example.football.models.dto.json.TownDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class TownServiceImpl implements TownService {
    private static final String FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private  final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readTownsFileContent(), TownDto[].class))
                .filter(townDto -> {
                    boolean isValid = validationUtil.isValid(townDto)
                            &&!isTownExist(townDto.getName());
                    sb.append(isValid ? String.format("Successfully imported Town %s - %d",townDto.getName(),townDto.getPopulation())
                            :"Invalid Town");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(townDto -> modelMapper.map(townDto, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    private boolean isTownExist(String name) {
        return townRepository.existsTownByName(name);
    }
}
