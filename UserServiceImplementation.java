package SpringProject.BusManagement.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringProject.BusManagement.Model.User;
import SpringProject.BusManagement.Repository.UserRepository;
@Service
public class UserServiceImplementation {
	@Autowired
	private UserRepository userRepository;
	

	public void addUser( User user) {
		
		this.userRepository.save(user);
		
	}

	public  Optional<User> getUserByEmail(String email) {
		
		return this.userRepository.findByuserEmail(email);
		
	}
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public User viewUser(Integer userId ) {
		Optional<User> user =  userRepository.findById(userId);  //null , or it can 5tg person rec
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
}
