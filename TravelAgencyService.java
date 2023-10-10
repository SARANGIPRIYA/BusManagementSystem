package SpringProject.BusManagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import SpringProject.BusManagement.Model.TravelAgency;

public interface TravelAgencyService {
	TravelAgency saveAgency(TravelAgency agency);
	TravelAgency getAgencyDetailsById(Integer agencyId);
	TravelAgency updateAgencyDetails(TravelAgency newAgency,Integer agencyId);
	//TravelAgency updateAgencyDetails(Integer agencyId);
	void deleteAgencyDetailsById(Integer agencyId);
	void deleteAllAgencyDetails();
	List<TravelAgency>getAllAgencyDetails();

}
