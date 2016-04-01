package com.rice.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rice.bean.Dish;
import com.rice.util.PageResult;

/**
 * 菜DAO
 * @author lyw
 *
 */
@Repository
public class DishDAO extends SuperDAO<Dish> {
	/**
	 * 查询分页
	 * @param map
	 * @return
	 */
	public PageResult<Dish> findByPageResult(Map<String,String> map){
		
		//where 条件
		String where=this.addFindWhere(map);
		
		//查询
		if(where.isEmpty())
			return this.findByPage(Dish.class,map, "");
		else
			return this.findByPage(Dish.class,map, " where "+where);
	}
	/**
	 * 添加查询where条件
	 * @param map
	 * @return
	 */
	private String addFindWhere(Map<String,String> map){
		//sql where
		StringBuffer sql=new StringBuffer();
		
		//第几周条件
		Object dish_week=map.get("dish_week");
		if(dish_week!=null&&(dish_week.toString().isEmpty()==false)){
			sql.append(" dish_week = ");
			sql.append(dish_week.toString());
		}
		//类型条件
		Object dish_type=map.get("dish_type");
		if(dish_type!=null&&(dish_type.toString().isEmpty()==false)){
			if("".equals(sql.toString())==false)
				sql.append(" and ");
			sql.append(" dish_type = '");
			sql.append(dish_type.toString());
			sql.append("'");
		}
		//菜名条件
		Object dish_name=map.get("dish_name");
		if(dish_name!=null&&dish_name.toString().isEmpty()==false){
			if("".equals(sql.toString())==false)
				sql.append(" and ");
			sql.append(" dish_name like '%");
			sql.append(dish_name.toString());
			sql.append("%'");
		}
		return sql.toString();
	}
	
}
