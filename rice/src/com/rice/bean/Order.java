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
 * 订单实体类
 * @author lyw
 *
 */
@DataTransferObject
@Entity
@Table(name="Order",catalog="rice")
public class Order {
	/**
	 * 唯一标识
	 */
	private Long id;
	/**
	 * 用户名称
	 */
	private String user_name;
	/**
	 * 订饭名称
	 */
	private String dish_name;
	/**
	 * 订饭数量
	 */
	private Integer dish_number;
	/**
	 * 订饭时间
	 */
	private Timestamp create_time;
	/**
	 * 是否付款
	 */
	private String isPayment;
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="user_name",nullable=false)
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	
	@Column(name="dish_name",nullable=false)
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dishName) {
		dish_name = dishName;
	}
	
	@Column(name="dish_number",nullable=false)
	public Integer getDish_number() {
		return dish_number;
	}
	public void setDish_number(Integer dishNumber) {
		dish_number = dishNumber;
	}
	
	@Column(name="create_time",nullable=false)
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}
	@Column(name="isPayment",nullable=false)
	public String getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(String isPayment) {
		this.isPayment = isPayment;
	}
	
}
