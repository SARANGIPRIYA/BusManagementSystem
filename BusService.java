package SpringProject.BusManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import SpringProject.BusManagement.Model.Bus;
/*BusService is an interface having following abstract methods  and 
implementation is provided in the BusServiceImplementation classs */
@Service
public interface BusService {
	Bus saveBusDetails(Bus bus);
	Bus getBusDetailsBybusId(int busId);
	void deleteAllBusDetails();
	Optional<Bus> deleteBusById(int busId);
	List<Bus> getAllBusDetails();
	Bus updateBusdetailsBybusId(Bus busdetails ,int busId);
}

