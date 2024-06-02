package com.example.football.service.impl;

import com.example.football.models.dto.xml.PlayerRootDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private  final ValidationUtil validationUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, StatRepository statRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count()>0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(FILE_PATH, PlayerRootDto.class)
                .getPlayers().stream()
                .filter(playerDto -> {
                    boolean isValid = validationUtil.isValid(playerDto)
                            && !emailExist(playerDto.getEmail());
                    sb.append(isValid ? String.format("Successfully imported Player %s %s - %s",playerDto.getFirstName(),playerDto.getLastName(),playerDto.getPosition())
                            :"Invalid Player");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(playerDto -> {
                    Player player = modelMapper.map(playerDto,Player.class);
                    player.setTown(findTownName(playerDto.getTown().getName()));
                    player.setTeam(findTeamName(playerDto.getTeam().getName()));
                    player.setStat(findStatId(playerDto.getStat().getId()));
                    return player;
                })
                .forEach(playerRepository::save);

        return sb.toString();
    }

    private Stat findStatId(Long id) {
        return statRepository.findStatById(id);
    }

    private Team findTeamName(String name) {
        return teamRepository.findTeamByName(name);
    }

    private Town findTownName(String name) {
        return townRepository.findTownByName(name);
    }

    private boolean emailExist(String email) {
        return playerRepository.existsPlayerByEmail(email);
    }

    @Override
    public String exportBestPlayers() {
        LocalDate after = LocalDate.of(1995,1,1);
        LocalDate before = LocalDate.of(2003,1,1);
        StringBuilder sb = new StringBuilder();
        playerRepository.findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after,before)
                .forEach(p ->{
                    sb.append(String.format("\"Player - %s %s\n" +
                            "\tPosition - %s\n" +
                            "\tTeam - %s\n" +
                            "\tStadium - %s\n",p.getFirstName(),p.getLastName(),
                            p.getPosition().name(),p.getTeam().getName(),p.getTeam().getStadiumName()));
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
