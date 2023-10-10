package SpringProject.BusManagement.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import SpringProject.BusManagement.Model.Bus;
/* @Repository Annotation is used to indicate particular class perform action on the object.
 * we can perform CRUD operation in JpaRepository. */

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{
	List<Bus> findByagencyNameContainingIgnoreCase(String agencyName);
	List<Bus> findByisAcBus(Boolean booleanObj);
	List<Bus>findBySourceAndDestination(String source,String destination);
}
