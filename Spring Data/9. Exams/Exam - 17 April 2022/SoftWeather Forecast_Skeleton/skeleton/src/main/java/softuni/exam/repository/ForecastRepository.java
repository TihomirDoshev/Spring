package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast,Long> {

   // boolean existsByDayOfWeekAndCity(String dayOfWeek,Long id);
    boolean existsByIdAndDayOfWeek(Long id,DayOfWeek dayOfWeek);
    //•	Filter only forecasts from sunday and from
    // cities with less than 150000 citizens,
    // order them by max temperature in descending order,
    // then by the forecast id in ascending order.

    List<Forecast> findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek day,Integer num);

}
