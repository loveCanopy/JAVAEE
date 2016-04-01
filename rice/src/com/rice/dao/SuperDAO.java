package com.rice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.rice.util.PageResult;
/**
 * 数据持久化工具类
 * @author lyw
 *
 * @param <T> 类型
 */
@Repository
public class SuperDAO<T> extends HibernateDaoSupport {
//	/**
//	 * 注入sessionFactory
//	 * @param sessionFactory
//	 */
//	@Resource(name = "sessionFactory")
//	public void setSuperSessionFactory(SessionFactory sessionFactory) {
//		super.setSessionFactory(sessionFactory);
//	}
	
	/**
	 * 添加
	 * @param obj
	 */
	public void doAdd(T t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 更新
	 * @param obj
	 */
	public void doUpdate(T t){
		this.getHibernateTemplate().update(t);
	}
	
	/**
	 * 删除
	 * @param obj
	 */
	public void doDelete(T t){
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 查找 分页数据
	 * @param T 类型
	 * @param map 
	 * @param where 查询sql条件 如 where name='abc'
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected PageResult<T> findByPage(Class T,Map<String,String> map,String where){
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
		PageResult<T> page=null;
		
		//实体类名
		String ClassName=T.getCanonicalName();
		ClassName=ClassName.substring(ClassName.lastIndexOf(".")+1,ClassName.length());
		
		//查询sql
		StringBuffer sql=new StringBuffer("from "+ClassName);
		//添加条件
		sql.append(" "+where);
		
		try {
			session=this.getSession();
			Query query = session.createQuery(sql.toString());
			
			//分页查询
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<T> list = query.list();
			
			//设置sql语句
			sql=new StringBuffer("select count(*) from "+ClassName);
			sql.append(" "+where);
			//查询总数据行数
			query= session.createQuery(sql.toString());
			int count = ((Number)query.uniqueResult()).intValue();
			
			//显示结果类
			page = new PageResult<T>();			
			//设置结果
			page.setRowCount(count);
			page.setData(list);
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
	 * 查找指定数据
	 * @param T 类型
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getById(Class T,long id){
		return (T) this.getHibernateTemplate().get(T.getClass(), id);
	}
}
