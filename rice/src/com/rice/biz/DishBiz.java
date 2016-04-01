package com.rice.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rice.bean.Dish;
import com.rice.dao.DishDAO;
import com.rice.util.DwrMethodRight;
import com.rice.util.PageResult;
import com.rice.util.Result;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 菜biz
 * @author lyw
 *
 */
@Service
@RemoteProxy
@Scope("prototype")
public class DishBiz {
	/**
	 * 菜DAO
	 */	
	@Resource //注入
	private DishDAO dishDao;

	public void setDishDao(DishDAO dishDao) {
		this.dishDao = dishDao;
	}
	
	/**
	 * 获取菜单列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RemoteMethod
	public PageResult<Dish> getAll(Map<String,String> parm,HttpServletRequest request){
		
		//第几周为空
		if(parm.get("dish_week")==null){
			//时间
			Timestamp time=new Timestamp(System.currentTimeMillis());
			//当前为第几周
			int dish_week=time.getDate()/7+1;
			if(dish_week==5)
				dish_week=1;
			//设置当前第几周
			parm.put("dish_week", String.valueOf(dish_week));
		}
		return this.dishDao.findByPageResult(parm);
	}
	/**
	 * 添加菜单
	 * @param parm
	 * @param request
	 * @return
	 */
	@RemoteMethod
	public Result doAdd(Map<String,String> parm,HttpServletRequest request){
		
		//是否具有权限
		boolean isRight=DwrMethodRight.methodRight(request);
		if(isRight==false)
			return new Result(false,"sorry，没有权限");
		
		Dish dish=new Dish();
		try {
			BeanUtils.populate(dish, parm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dish!=null){
			this.dishDao.doAdd(dish);
			if(dish.getId()!=null)
				return new Result(true,"恭喜..添加菜单成功");
		}
		return new Result(false,"sorry...添加菜单失败");
	}
	/**
	 * 删除菜单
	 * @param parm
	 * @param request
	 * @return
	 */
	@RemoteMethod
	public Result doDelete(List<Dish> list,HttpServletRequest request){
		//是否具有权限
		boolean isRight=DwrMethodRight.methodRight(request);
		if(isRight==false)
			return new Result(false,"sorry，没有权限");
		
		try {
			for (Dish dish : list) {
				this.dishDao.doDelete(dish);
			}
			return new Result(true,"删除成功");	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new Result(false,"删除失败");	
	}
	/**
	 * 更改菜单
	 * @param parm
	 * @param request
	 */
	@RemoteMethod
	public Result doUpdate(Map<String,String> parm,HttpServletRequest request){
		//是否具有权限
		boolean isRight=DwrMethodRight.methodRight(request);
		if(isRight==false)
			return new Result(false,"sorry，没有权限");
		
		Dish dish=new Dish();
		try {
			BeanUtils.populate(dish,parm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dish!=null&&dish.getId()!=null){
			this.dishDao.doUpdate(dish);
			return new Result(true,"更改菜单成功");
		}
		return new Result(true,"更改菜单失败");
	}
}
