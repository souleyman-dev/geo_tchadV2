package td.geo.geo_tchad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import td.geo.geo_tchad.entity.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    List<Departement> findByProvinceId(Long provinceId);
    
}
