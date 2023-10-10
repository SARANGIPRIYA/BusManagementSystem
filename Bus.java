package SpringProject.BusManagement.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/*@Entity are objects that stores as a record in database.
 * @Table Which is used to change the table name in Database side.
 * @DynamicUpdate Annotation ensures that hibernate uses only modified columns in SQL.
 * @Id annotation Specifies the primary key of an entity in database.
 * @GeneratedValue Annotation will generate something generally used in conjunction with @id to automatically generate PriaryKey column.
 * @Column Annotation is used for adding column name in database explicitily.*/

@Entity
@Table(name="bus_management")
@DynamicUpdate
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int busId;
	@Column(name="driver_Name",nullable=true)
	private String driverName;
	private String busNumber;
	//private String dateOfTravelling;
	@Column(name="Starting_point",nullable=false)
	private String source;
	@Column(name="Ending_point",nullable=false)
	private String destination;
	@Column(name="agency_name",nullable=false)
    private String agencyName;
	@Column(name="ticket_price",nullable=false)
//	private BigDecimal ticketCost;
	private int ticketCost;
	@Column(name="total_seats",nullable=false)
	private int totalSeats;
	@Column(name="Seats_booked",nullable=false)
	private int noOfSeatsBooked;
	private Boolean isAcBus;
	@Column(name="rating",nullable=false)
	private int busRating;
	@Column(name="Seats_available",nullable=false)
	private int noOfSeatsAvailable;

	//@JsonBackReference
		//@JsonIgnore
		@JoinColumn(name="routeId")
		@ManyToOne
		private Route route;
		
		//@JsonIgnore
		@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)
		private List<Feedback> review= new ArrayList<>();
		
		@JoinColumn(name="agencyId")
		@ManyToOne
		private TravelAgency travelAgency;	
		

		public int getBusId() {
			return busId;
		}

		@Override
		public String toString() {
			return "Bus [busId=" + busId + ", driverName=" + driverName + ", busNumber=" + busNumber + ", source="
					+ source + ", destination=" + destination + ", agencyName=" + agencyName + ", ticketCost="
					+ ticketCost + ", totalSeats=" + totalSeats + ", noOfSeatsBooked=" + noOfSeatsBooked + ", isAcBus="
					+ isAcBus + ", busRating=" + busRating + ", noOfSeatsAvailable=" + noOfSeatsAvailable + ", route="
					+ route + ", review=" + review + ", travelAgency=" + travelAgency + "]";
		}

		public void setBusId(int busId) {
			this.busId = busId;
		}

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}

		public String getBusNumber() {
			return busNumber;
		}

		public void setBusNumber(String busNumber) {
			this.busNumber = busNumber;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getAgencyName() {
			return agencyName;
		}

		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
		}

		public int getTicketCost() {
			return ticketCost;
		}

		public void setTicketCost(int ticketCost) {
			this.ticketCost = ticketCost;
		}

		public int getTotalSeats() {
			return totalSeats;
		}

		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}

		public int getNoOfSeatsBooked() {
			return noOfSeatsBooked;
		}

		public void setNoOfSeatsBooked(int noOfSeatsBooked) {
			this.noOfSeatsBooked = noOfSeatsBooked;
		}

		public Boolean getIsAcBus() {
			return isAcBus;
		}

		public void setIsAcBus(Boolean isAcBus) {
			this.isAcBus = isAcBus;
		}

		public int getBusRating() {
			return busRating;
		}

		public void setBusRating(int busRating) {
			this.busRating = busRating;
		}

		public int getNoOfSeatsAvailable() {
			return noOfSeatsAvailable;
		}

		public void setNoOfSeatsAvailable(int noOfSeatsAvailable) {
			this.noOfSeatsAvailable = noOfSeatsAvailable;
		}

		public Route getRoute() {
			return route;
		}

		public void setRoute(Route route) {
			this.route = route;
		}

		public List<Feedback> getReview() {
			return review;
		}

		public void setReview(List<Feedback> review) {
			this.review = review;
		}

		public TravelAgency getTravelAgency() {
			return travelAgency;
		}

		public void setTravelAgency(TravelAgency travelAgency) {
			this.travelAgency = travelAgency;
		}
		@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
		private List<BusBooking> booking = new ArrayList<>();


		public List<BusBooking> getBooking() {
			return booking;
		}

		public void setBooking(List<BusBooking> booking) {
			this.booking = booking;
		}

		

	
}










