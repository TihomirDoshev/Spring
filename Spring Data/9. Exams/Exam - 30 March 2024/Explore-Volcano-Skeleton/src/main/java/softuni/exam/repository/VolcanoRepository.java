package softuni.exam.repository;

import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.List;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano,Long> {
    boolean existsVolcanoByName(String name);
    boolean existsVolcanoById(Long id);

    Volcano findVolcanoById(Long id);
    @Query(value = "SELECT v FROM Volcano v WHERE v.isActive = TRUE AND v.elevation > 3000 AND v.lastEruption IS NOT NULL ORDER BY v.elevation DESC")
    List<Volcano>findAllByActiveAndElevationMoreThanAndLastEruptionIsNotNullOrderByElevationDesc();

}
