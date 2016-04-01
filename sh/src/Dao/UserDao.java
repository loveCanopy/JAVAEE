package Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateTools;
import sh.User;

public class UserDao {

	public List<User> getAll(){
		Session session=new HibernateTools().getSession();
		Query createQuery = session.createQuery("from User");
		List<User> list = createQuery.list();
		return list;
	};
	
	public User login(User user){
		Session session=new HibernateTools().getSession();
		User user_=(User)session.get(User.class, 1);
		return user_;
	}
	
	
	
}
