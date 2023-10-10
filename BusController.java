package SpringProject.BusManagement.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringProject.BusManagement.Model.Bus;
import SpringProject.BusManagement.Model.Route;
import SpringProject.BusManagement.Repository.TravelAgencyRepository;
import SpringProject.BusManagement.Repository.BusRepository;
import SpringProject.BusManagement.Repository.RouteRepository;
import SpringProject.BusManagement.ServiceImplementation.BusServiceImplementation;
@CrossOrigin
@RestController
/*@RestContoller annotation  is used to simplify the creation of RESTful web services.It's a conveninece annotation which is combination
 *  of @Controller and @ResponseBody.*/
@RequestMapping("/BusBooking.com")
/* @RequestMapping annotation which is used to map HTTP request to handler methods of MVC and REST controllers.*/
public class BusController {
	/* @AutoWired Annotation is used for automatic dependency injection. */
	@Autowired
	private TravelAgencyRepository agencyRepository;
	@Autowired
	private BusServiceImplementation busService;
	public  BusController(BusServiceImplementation busService) {
		this.busService =busService;
	}

	/*@PostMapping  annotated method handles the HTTP POST request matched with given URL expression
	 * create and save table in database.*/
	@PostMapping("/saveBusDetails/{agencyId}")
	public ResponseEntity<Bus> saveBus(@PathVariable("agencyId") int agencyId,@RequestBody Bus bus) {
		try {
			agencyRepository.findById(agencyId).map(travelAgency -> {
				bus.setTravelAgency(travelAgency);
			return new ResponseEntity<Bus>(busService.saveBusDetails(bus), HttpStatus.CREATED);
			}).orElseThrow(() -> new Exception("Internal_Server_Error"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		}
	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get data from database*/
	@GetMapping("/getBusDetailsBybusId/{busId}")

	public ResponseEntity<Bus>getBusDetailsBybusId(@PathVariable int busId) {
		try {
			return new ResponseEntity<Bus>(busService.getBusDetailsBybusId(busId),HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get data from database*/
	@GetMapping("/getAllBusDetails")
	public ResponseEntity<List<Bus>> getAllbuses(@RequestParam(required = false) String agencyName) {
		try {
			List<Bus> bus = new ArrayList<Bus>();

			if (agencyName == null)
				busService.getAllBusDetails().forEach(bus::add);
			else
		busService.getAcorNonAcBusDetails(agencyName).forEach(bus::add);

			if (bus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*@PutMapping  is used for handling the incoming put request from client side.
	 * update details by busId in database*/
	@PutMapping("/updateBusDetailsbyId/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable Integer busId, @RequestBody Bus bus){
		try {
			return new ResponseEntity<>(busService.updateBusdetailsBybusId(bus,busId), HttpStatus.OK);

		}
		catch(Exception exc) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/* @GetMapping annotation for mapping HTTP GET request onto specific handler methods.
	 * get data from database*/
	@GetMapping("/sortByCost/{direction}")
	public ResponseEntity<List<Bus>> getAllBusesBySortingCost(@PathVariable("direction") String direction, @RequestParam(required = true)String fieldName){
		try {
			List<Bus> bus = new ArrayList<Bus>();
			bus = busService.sortByCost(direction,fieldName);
			if(bus.isEmpty())
				return new ResponseEntity<List<Bus>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<Bus>>(bus,HttpStatus.OK);

		}
		catch(Exception exc) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get data from database*/
	@GetMapping("/sortByRating/{direction}")
	public ResponseEntity<List<Bus>> getAllBusesByRatingSort(@PathVariable("direction") String direction, @RequestParam(required = true)String fieldName){
		try {
			List<Bus> bus = new ArrayList<Bus>();
			bus = busService.sortByRatings(direction,fieldName);
			if(bus.isEmpty())
				return new ResponseEntity<List<Bus>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<Bus>>(bus,HttpStatus.OK);

		}
		catch(Exception exc) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get data from database*/
	@GetMapping("/searchBusesBysourceAnddestination")
	public ResponseEntity<List<Bus>> getBusbySourceAndDestination(@RequestParam(required = true) String source,String destination){
		try {
			List<Bus> bus = busService.fetchBySourceAndDestination(source,destination);

			if (bus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bus, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	/* @DeleteMapping  annotation maps HTTP DELETE request onto specific hander methods.
	 * delete details by busid from database*/
	@DeleteMapping("/deleteBusBybusId/{busId}")
	public ResponseEntity<Bus>deleteBusById(@PathVariable("busId")int busId) {

		Optional<Bus> busData =busService.deleteBusById(busId);
		try {
			if (busData.isPresent()) {
				busService.deleteBusById(busId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* @DeleteMapping annotation maps HTTP DELETE request onto specific hander methods.
	 * delete all the bus details from database. */
	@DeleteMapping("/deleteAllBusDetails")
	public ResponseEntity<Bus>deleteAllBusDetails() {
		try {
			busService.deleteAllBusDetails();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get details acBus details from database. */
	@GetMapping("/BusManagement/isAcBus")
	public ResponseEntity<List<Bus>> findByacBus(){
		try {
			List<Bus>bus=busService.findByacBusDetails(true);
			if(bus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bus,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
	 * get details NonacBus details from database. */
	@GetMapping("/BusManagement/nonAcBus")
	public ResponseEntity<List<Bus>> findByNonacBus(){
		try {
			List<Bus>bus =busService.findByNonacBusDetails(false);
			if(bus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bus,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}		
	
	}






