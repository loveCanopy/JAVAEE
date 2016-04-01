package com.rice.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * dwr访问方法的权限
 * @author Giant
 *
 */
public class DwrMethodRight {
	/**
	 * dwr访问方法的权限
	 * @使用描述 在需要控制权限方法内加入即可
	 * @param request 
	 * @return 是否具有权限
	 */
	public static boolean methodRight(HttpServletRequest request){
		HttpSession session=request.getSession();
		//获取admin属性
		Object admin=session.getAttribute("admin");
		if(admin!=null)
			return true;
		return false;
	}
}
