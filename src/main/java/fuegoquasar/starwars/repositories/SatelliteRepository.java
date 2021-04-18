package fuegoquasar.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fuegoquasar.starwars.models.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Long>{
    
    @Query(value="SELECT * FROM satellite s WHERE s.name = ?1 ORDER BY s.created_at DESC", nativeQuery = true)
    Satellite findLastByName(String name);
}
