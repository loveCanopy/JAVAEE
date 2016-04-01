package Utils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import sh.Project;
import sh.User;

public class CreatDB {
    @Test
	public void testName(){
		// TODO Auto-generated method stub

		User user=new User();
		User user1=new User();
		Project project=new Project();
		
		user.setUsername("yujie");
		user.setPassword("123456");
		
		user1.setUsername("yutong");
		user1.setPassword("123456");
		
		project.setProname("数据库开发");
		user.getProjects().add(project);
		user1.getProjects().add(project);
		HibernateTools hibernateTools=new HibernateTools();
		Session session = hibernateTools.getSession();
		Transaction ts=session.beginTransaction();
		session.save(project);
		session.save(user);
		session.save(user1);
		ts.commit();
		session.close();
		
		
	}

}
