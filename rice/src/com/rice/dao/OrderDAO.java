package com.rice.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.rice.bean.Order;
import com.rice.util.PageResult;
/**
 * 订单DAO
 * @author lyw
 *
 */
@Repository
public class OrderDAO extends SuperDAO<Order> {
	/**
	 * 查询分页
	 * @param map
	 * @return
	 */
	public PageResult<Order> findByPageResult(Map<String,String> map){
		
		//where 条件
		String where=this.addFindWhere(map);
		
		//查询
		if(where.isEmpty())
			return this.findByPage(Order.class,map, "");
		else
			return this.findByPage(Order.class,map, " where "+where);
	}
	
	/**
	 * 添加查询where条件
	 * @param map
	 * @return
	 */
	private String addFindWhere(Map<String, String> map) {
		// TODO Auto-generated method stub
		//sql where
		StringBuffer sql=new StringBuffer();
		//时间条件
		Object create_time=map.get("create_time");
		if(create_time!=null&&(create_time.toString().isEmpty()==false)){
			sql.append(" create_time >= ' ");
			sql.append(create_time.toString());
			sql.append("'");
		}
		return sql.toString();
	}
	/**
	 * 今日统计信息
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Map<String,Object>> todayStatistics(){
		//格式化今日时间
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date=dateFormat.format(new Date());
		/*
		 * 统计sql
		 * 【dish_name：菜名 】【user_name：订餐人】【user_count：订餐人订的每种菜数量】【dish_count 每种菜被订数量】
		 */
		StringBuffer sql=new StringBuffer();
		sql.append(" select dish_name,user_name,user_count,dish_count ");
		sql.append(" from `order` LEFT JOIN ( ");
		sql.append("	select user_name as o_name,sum(dish_number) as user_count from `order`  ");
		sql.append(" 		where  `order`.create_time>'"+date+"'");
		sql.append("		GROUP BY user_name");
		sql.append(" ) o ");
		sql.append(" on (o.o_name=`order`.user_name) LEFT JOIN ( ");
		sql.append("	select dish_name dn,sum(dish_number) dish_count from `order` ");
		sql.append(" 		where  `order`.create_time>'"+date+"'");
		sql.append("		GROUP BY dish_name");
		sql.append(" ) di on di.dn=`order`.dish_name ");		
		sql.append(" where  `order`.create_time>'"+date+"'");
		
		Session session=null;
		try {
			session = this.getSession();
			SQLQuery query=session.createSQLQuery(sql.toString());
			//查询类型为map
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
