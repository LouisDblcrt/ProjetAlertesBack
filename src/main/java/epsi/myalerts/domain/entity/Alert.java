package epsi.myalerts.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alert")
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank
	private String name;

	private String description;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JoinColumn(name = "alert_date")
	private Date alert_date;

	@NotBlank
	private String lieu;

	@OneToOne
	@JoinColumn(name = "alert_owner")
	private AlertOwner alert_owner;

	
	@Enumerated(EnumType.ORDINAL)
	private Criticite criticite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAlert_date() {
		return alert_date;
	}

	public void setAlert_date(Date alert_date) {
		this.alert_date = alert_date;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public AlertOwner getAlert_owner() {
		return alert_owner;
	}

	public void setAlert_owner(AlertOwner alert_owner) {
		this.alert_owner = alert_owner;
	}

	public Criticite getCriticite() {
		return criticite;
	}

	public void setCriticite(Criticite criticite) {
		this.criticite = criticite;
	}

}
