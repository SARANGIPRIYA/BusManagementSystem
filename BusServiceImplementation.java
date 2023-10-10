package SpringProject.BusManagement.ServiceImplementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import SpringProject.BusManagement.Model.Bus;
import SpringProject.BusManagement.Repository.BusRepository;
import SpringProject.BusManagement.Service.BusService;

/* @Service Annotation , we can clearly denote which classes in our application are responsible for
 * performing specific services and functions. */
@Service
public class BusServiceImplementation implements BusService {
	/*creating bean for busRepository and busServiceImplementation.
	 *@Autowired annotation is used for automatic dependency injection. */ 
	@Autowired
	private BusRepository busRepository;
	
	public BusServiceImplementation(BusRepository busRepository) {
		this.busRepository = busRepository ;

	}
	// save the records in database with the help of RESTAPI.
	@Override
	public Bus saveBusDetails(Bus bus) {
		return busRepository.save(bus);
	}
	//get records from database by busId  with help of RESTAPI.
	@Override
	public Bus getBusDetailsBybusId( int busId){
		Optional<Bus> bus = busRepository.findById(busId); 
		if(bus.isPresent()) {
			return bus.get();
		}
		else {
			return null;
		}
	}
	@Override
	public List<Bus>getAllBusDetails(){
		return busRepository.findAll();
	}
	//update the exsisting values
	public Bus updateBusdetailsBybusId(Bus busdetails ,int busId) {
		Optional<Bus> bus =busRepository.findById(busId);
		if(bus.isPresent()) {
			Bus existingbus=bus.get();
			existingbus.setSource(busdetails.getSource());
			existingbus.setDestination(busdetails.getDestination());
			existingbus.setBusRating(busdetails.getBusRating());
			existingbus.setBusNumber(busdetails.getBusNumber());
			existingbus.setIsAcBus(busdetails.getIsAcBus());
			existingbus.setAgencyName(busdetails.getAgencyName());
			existingbus.setTicketCost((busdetails.getTicketCost()));
			existingbus.setBusRating((busdetails.getBusRating()));
			existingbus.setTotalSeats(busdetails.getTotalSeats());
			existingbus.setNoOfSeatsAvailable(busdetails.getNoOfSeatsAvailable());
			existingbus.setNoOfSeatsBooked(busdetails.getNoOfSeatsBooked());
			existingbus.setDriverName(busdetails.getDriverName());
			
			busRepository.save(existingbus);
			return existingbus;
		}
		else {
			return null;

		}
	}
	//get bus details by source and destination
	public List<Bus> fetchBySourceAndDestination(String source,String destination){
		List<Bus> bus = new ArrayList<Bus>();
		busRepository.findBySourceAndDestination(source, destination).forEach(bus::add);
		return bus;
	}
	//delete records from database by busId with help of RESTAPI
	@Override
	public Optional<Bus> deleteBusById(int busId) {
		Optional<Bus> bus = busRepository.findById(busId);  
		if(bus.isPresent()) {
			busRepository.deleteById(busId);
			{
				return bus;
			}	
		}
		return null;

	}
	//delete all records from database with help of RESTAPI.
	@Override
	public void deleteAllBusDetails() {
		busRepository.deleteAll();
		System.out.println("All Bus Deleted");
	}

	//get records from database by flitering acBus
	public List<Bus>findByacBusDetails(Boolean booleanObj) throws Exception{
		List<Bus>bus=busRepository.findByisAcBus(true);
		try {
			if(bus.isEmpty())
				return bus;
		}
		catch(Exception exe) {
			return (List<Bus>) exe;
		}return bus;
	}
	//get records from database by flitering NonacBus
	public List<Bus>findByNonacBusDetails(Boolean booleanObj) throws Exception{
		List<Bus>bus= busRepository.findByisAcBus(false);
		try {
			if(bus.isEmpty())
				return bus;
		}
		catch(Exception exe) {
			return (List<Bus>) exe;
		}
		return bus;
	}
	
	//sort by direction whether ascending /descending order
	public Sort.Direction sortByDirection(String fieldName,String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}
	//sort by direction whether ascending /descending order
	public  List<Bus> sortByRatings(String direction, String fieldName) {

		List<Bus> bus = new ArrayList<Bus>();
		bus = busRepository.findAll(Sort.by(getSortDirection(direction), fieldName));
		return bus;
	}
	//sort by direction ascending or descending
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}
//sort by cost ascending or descending

	public  List<Bus> sortByCost(String direction, String fieldName) {
		List<Bus> bus = new ArrayList<Bus>();
		bus = busRepository.findAll(Sort.by(getSortDirection(direction), fieldName));
		return bus;
	}
	public List<Bus> getAcorNonAcBusDetails(String agencyName) {
		return busRepository.findByagencyNameContainingIgnoreCase(agencyName);
	}
	
	
}












