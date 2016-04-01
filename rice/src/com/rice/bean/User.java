package com.rice.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
/**
 * 用户实体类
 * @author lyw
 *
 */
@DataTransferObject
@Entity
@Table(name="users",catalog="rice")
public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 班级
	 */
	private String banji;
	/**
	 * 生日
	 */
	private Timestamp birth;
	public User() {
		super();
	}
	public User(String name, String password, String sex, String email,
			String banji, Timestamp birth) {
		super();
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.banji = banji;
		this.birth = birth;
	}
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="sex",nullable=false)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="banji",nullable=false)
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	@Column(name="birth",nullable=false)
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}	
	
}
