package com.rice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import sun.security.util.Password;

import com.rice.bean.User;
import com.rice.util.PageResult;
/**
 * 用户DAO
 * @author lyw
 *
 */
@Repository
public class UserDAO extends SuperDAO<UserDAO> {
	/**
	 * 获取用户
	 * @param username
	 * @return
	 */
	public User get(String username){
		Session session= this.getSession();
		User user=null;
		try {
			Query query = session.createQuery("from User where name=:name");
			query.setString("name", username);
			user=(User) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			releaseSession(session);
		}
		return user;
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll(){
		return this.getHibernateTemplate().find("from User");
	}
	
	/**
	 * 注册用户
	 * @param user
	 */
	public void save(User user){
		this.getHibernateTemplate().save(user);
	}
	
	/**
	 * 更新用户
	 * @param user
	 */
	public void update(User user){
		this.getHibernateTemplate().update(user);
	}
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(User user){
		this.getHibernateTemplate().delete(user);
	}
	
	/**
	 * 获取分页列表
	 * @param map srart:开始行 limit:多少行
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageResult<User> getAll(Map<String,String> map){
		if(map==null)
			map=new HashMap<String,String>();
		
		int start=0;//开始行
		int limit=5;//每页显示多少行
		try {
			start = Integer.parseInt(map.get("start"));
			limit = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
		}
		
		Session session=null;
		//放入pageResult
		PageResult<User> page=null;
		//查询sql
		String sql="from User";
		//添加where条件
		sql=this.addSqlWhere(sql, map);
		try {
			session=this.getSession();
			Query query = session.createQuery(sql);
			//设置查询属性值
			this.setQueryAttribute(query, map);
			
			//分页查询
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<User> list = query.list();
			
			//设置sql语句
			sql=this.addSqlWhere("select count(*) from User", map);
			//查询总数据行数
			query= session.createQuery(sql);
			//设置查询属性值
			this.setQueryAttribute(query, map);
			int count = ((Number)query.uniqueResult()).intValue();
			
			//显示结果类
			page = new PageResult<User>();			
			//设置结果
			page.setRowCount(count);
			page.setData(list);
			//System.out.println(list.size()+map.get("username"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//关闭session
			if(session!=null)
				session.close();
		}
		
		return page;
	}
	
	/**
	 * 设置查询属性值
	 * @param query
	 * @param map
	 */
	private void setQueryAttribute(Query query,Map<String,String> map){
		//设置条件
		if(StringUtils.isNotEmpty(map.get("username")))
			query.setString("name",map.get("username"));
		if(StringUtils.isNotEmpty(map.get("banji")))
			query.setString("banji", "%"+map.get("banji")+"%");
	}
	
	/**
	 * 添加where条件
	 * @param map
	 */
	private String addSqlWhere(String sql,Map<String,String> map){
		//查询条件
		String where="";
		
		//添加条件
		if(StringUtils.isNotEmpty(map.get("username")))
			where=" name = :name ";
		if(StringUtils.isNotEmpty(map.get("banji"))){
			if(!where.equals(""))
				where+=" and ";
			where+=" banji like :banji ";
		}
		if(!where.equals(""))
			where=" where "+where;
		
		//拼接sql语句
		sql+=where;
		//System.out.println(sql);
		return sql;
	}
}
