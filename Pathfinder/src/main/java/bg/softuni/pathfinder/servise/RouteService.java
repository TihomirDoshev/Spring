package bg.softuni.pathfinder.servise;


import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.entites.Picture;
import bg.softuni.pathfinder.model.entites.Route;
import bg.softuni.pathfinder.repository.RouteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteService {
    private RouteRepository routeRepository;
    private Random random;
    private ModelMapper modelMapper;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.random = new Random();
        this.modelMapper = new ModelMapper();
    }
    @Transactional
    public List<RouteShortInfoDTO>getAll(){
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }


//    @Transactional
//    public RouteShortInfoDTO getRandomRoute(){
//        Long routeCount = routeRepository.count();
//        long randomId = random.nextLong(routeCount) + 1;
//        Optional<Route> byId = routeRepository.findById(randomId);
//        return mapToShortInfo(byId.get());
//    }
    private RouteShortInfoDTO mapToShortInfo(Route route) {

        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);
        Optional<Picture> first = route.getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());
        return dto;
    }

}
