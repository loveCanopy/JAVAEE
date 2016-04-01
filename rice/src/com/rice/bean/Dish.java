package com.rice.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
/**
 * 菜实体类
 * @author lyw
 *
 */
@DataTransferObject
@Entity
@Table(name="Dish",catalog="rice")
public class Dish {
	/**
	 * 唯一标识
	 */
	private Long id;
	/**
	 * 菜名
	 */
	private String dish_name;
	/**
	 * 星期、周
	 */
	private Integer dish_week;
	/**
	 * 价格
	 */
	private Double  dish_price;
	/**
	 * 类型
	 */
	private String dish_type;
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="dish_name",nullable=false)
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dishName) {
		dish_name = dishName;
	}
	@Column(name="dish_week",nullable=false)
	public Integer getDish_week() {
		return dish_week;
	}
	public void setDish_week(Integer dishWeek) {
		dish_week = dishWeek;
	}
	@Column(name="dish_price",nullable=false)
	public Double getDish_price() {
		return dish_price;
	}
	public void setDish_price(Double dishPrice) {
		dish_price = dishPrice;
	}

	@Column(name="dish_type",nullable=false)
	public String getDish_type() {
		return dish_type;
	}
	public void setDish_type(String dishType) {
		dish_type = dishType;
	}
	
	
}
