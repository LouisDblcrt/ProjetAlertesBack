package epsi.myalerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
/*
 * 
create table alert_owner(
id int primary key auto_increment,
enterprise varchar(150) not null,
description varchar(300),
priority_max int not null
);
 */
@Entity
@Table(name="alert_owner")
public class AlertOwner {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String enterprise;
	
	private String description;
	
	@Column(name="priority_max")
	@NotBlank
	private int priorityMax;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriorityMax() {
		return priorityMax;
	}

	public void setPriorityMax(int priorityMax) {
		this.priorityMax = priorityMax;
	}
	
	
	
}
