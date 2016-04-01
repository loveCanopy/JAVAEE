package com.rice.biz;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rice.bean.User;
import com.rice.dao.UserDAO;
import com.rice.util.PageResult;
import com.rice.util.Result;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 用户 biz
 * @author lyw
 *
 */
@Service
@RemoteProxy
@Scope("prototype")
public class UserBiz {
	
	/**
	 * 用户DAO
	 */
	@Resource //注入
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@RemoteMethod
	public Result login(Map<String,String> parm,HttpServletRequest request){
		/*
		 *用户名密码 
		 */
		Object username=parm.get("username");
		Object password=parm.get("password");
		if(username==null||password==null)
			return new Result(false,"sorry，参数不能为空");
		
		User user=this.userDAO.get(username.toString());
		if(user==null)
			return new Result(false,"sorry,登陆失败");
		
		//密码验证
		if(password.toString().equals(user.getPassword())){
			request.getSession().setAttribute("admin", "admin");
			return new Result(true,"登陆成功，您现在具有管理员的权限了。");
		}
		return new Result(false,"sorry,登陆失败");
	}
	/**
	 * 获取用户列表
	 * @param request
	 * @return
	 */
	public PageResult<User> getAll(Map<String,String> parm,HttpServletRequest request){
		return this.userDAO.getAll(parm);
	}
	
	/**
	 * 修改用户信息
	 * @param parm
	 * @param request
	 * @return
	 */

	public Result doUpdate(Map<String,String> parm,HttpServletRequest request){
		User user=new User();
		try {
			//将参数生成为对象
			BeanUtils.populate(user, parm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null&&user.getId()!=null){
			//修改用户
			this.userDAO.update(user);
			return new Result(true,"修改成功！");
		}
		return new Result(false,"修改失败！");
	}
	
	/**
	 * 注册用户
	 * @return
	 */
	public Result doReg(Map<String,String> parm,HttpServletRequest request){
		User user=new User();
		try {
			//将参数生成为对象
			BeanUtils.populate(user,parm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null){
			this.userDAO.save(user);
			if(user.getId()!=null)
				return new Result(true,"保存成功");
		}
		return new Result(false,"保存失败");
	}
	
	/**
	 * 删除数据
	 * @param list
	 * @param request
	 * @return
	 */
	public Result doDelete(List<User> list,HttpServletRequest request){
		try {
			for (User user : list) {
				this.userDAO.delete(user);
			}

			return new Result(true,"删除成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false,"删除失败");
	}
}
