package epsi.myalerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="alert_owner")
public class AlertOwner {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	private String enterprise;
	
	private String description;
	
	@Column(name="priority_max")
	@NotNull
	private int priorityMax;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
