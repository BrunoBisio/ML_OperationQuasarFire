package fuegoquasar.starwars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fuegoquasar.starwars.models.Satellite;

public interface ISatelliteRepository extends JpaRepository<Satellite, Long>{
    
    @Query("select top 1 s from Satellite s where s.name = ?1 order by s.createdAt Desc")
    Satellite findLastByName(String name);
}
