package com.example.football.service.impl;

import com.example.football.models.dto.json.TeamDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private  final ValidationUtil validationUtil;

    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count()>0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();
       Arrays.stream(gson.fromJson(readTeamsFileContent(), TeamDto[].class))
               .filter(teamDto -> {
                   boolean isValid = validationUtil.isValid(teamDto)
                           &&!isTeamNameExist(teamDto.getName());
                   sb.append(isValid ?String.format("Successfully imported Team %s - %s",teamDto.getName(),teamDto.getFanBase())
                           :"Invalid Team");
                   sb.append(System.lineSeparator());
                   return isValid;
               })
               .map(teamDto -> {
                   Team team = modelMapper.map(teamDto,Team.class);
                   team.setTown(findTownName(teamDto.getTownName()));
                   return team;
               })
               .forEach(teamRepository::save);

        return sb.toString();
    }

    private Town findTownName(String name) {
        return townRepository.findTownByName(name);
    }

    private boolean isTeamNameExist(String name) {
        return teamRepository.existsTeamByName(name);
    }
}
