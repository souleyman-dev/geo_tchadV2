package td.geo.geo_tchad.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import td.geo.geo_tchad.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Optional<Province> findByCode(String code);

    @EntityGraph(attributePaths = "departements")
    Optional<Province> findWithDepartementsById(Long id);
}