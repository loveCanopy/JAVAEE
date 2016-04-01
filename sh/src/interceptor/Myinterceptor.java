package interceptor;

import javax.enterprise.event.Observes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Myinterceptor extends AbstractInterceptor{

	
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		
		ActionContext ac=arg0.getInvocationContext();
		ActionProxy proxy = arg0.getProxy();
		String actionName = proxy.getActionName();
//		System.out.println(actionName);
		String method=proxy.getMethod();
//		System.out.println(method);
		
		if(actionName.equals("user_login")){
			return arg0.invoke();
		}else{
			Object object = ac.getSession().get("user");
			System.out.println(object);
			if(object!=null){
				return arg0.invoke();
			}else{
				return "fail"; 
			}
			
			
		}
		
      
	}

	
	
}
