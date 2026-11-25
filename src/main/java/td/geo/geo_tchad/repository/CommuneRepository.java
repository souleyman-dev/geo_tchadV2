package td.geo.geo_tchad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import td.geo.geo_tchad.entity.Commune;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    
}
