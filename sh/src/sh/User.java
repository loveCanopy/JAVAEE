package sh;

import java.util.HashSet;
import java.util.Set;

public class User {

	private int userid;
	private String username;
	private String password;
	private Set<Project> projects=new HashSet<Project>();
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return this.getUsername();
//	}
//	
	
}
