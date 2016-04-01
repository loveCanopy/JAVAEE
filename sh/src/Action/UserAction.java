package Action;

import java.util.List;
import java.util.Map;

import sh.User;
import Service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction  extends ActionSupport{

	
	private UserService userService=new UserService();
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login(){
		User user1=userService.login(user);
		if(user1==null){
			return "fail";
		}else{
			ActionContext.getContext().getSession().put("user", user1);
			return "success";
		}
		
		
	}
	
	public String list(){
		List<User> list=userService.getAll();
		if(list==null){
			return "fail";
		}else{
			Map<String, Object> contextMap = ActionContext.getContext().getContextMap();
			contextMap.put("list", list);
			return "list";
			
		}
		
		
	}
	
	
	
}
