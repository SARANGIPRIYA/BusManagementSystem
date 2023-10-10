package SpringProject.BusManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringProject.BusManagement.ServiceImplementation.EmailService;
@RestController
public class EmailController {
//@Autowired
//private EmailService emailService;
//@RequestMapping("/")
//public ResponseEntity<String> checkEmail()
//{
//	emailService.sendEmail("sarangijyoshnapriya@gmail.com","Check","Checking Email");
//	return new ResponseEntity<>("Message send",HttpStatus.CREATED);
//}
	@Autowired
	private EmailService emailService;
	@RequestMapping("/")
	public ResponseEntity<String> checkEmail()
	{
	emailService.sendEmail("sarangijyoshnapriya@gmail.com", "Check", "Checking Email");
	return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}
}
