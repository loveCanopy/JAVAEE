package Service;

import java.util.List;

import Dao.UserDao;
import sh.User;

public class UserService {

	private UserDao userdao=new UserDao();
	public List<User> getAll(){
		
	  return userdao.getAll();
	}
	
	
	public User login(User user){
	
		return userdao.login(user);
		
	}
}
