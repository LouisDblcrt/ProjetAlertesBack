package epsi.myalerts.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SubscriptionPK implements Serializable{

	protected Integer id_user;
	protected Integer id_alert_owner;

	public SubscriptionPK() {

	}

	public SubscriptionPK(Integer id_user, Integer id_alert_owner) {
		this.id_user = id_user;
		this.id_alert_owner = id_alert_owner;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Integer getId_alert_owner() {
		return id_alert_owner;
	}

	public void setId_alert_owner(Integer id_alert_owner) {
		this.id_alert_owner = id_alert_owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_alert_owner == null) ? 0 : id_alert_owner.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionPK other = (SubscriptionPK) obj;
		if (id_alert_owner == null) {
			if (other.id_alert_owner != null)
				return false;
		} else if (!id_alert_owner.equals(other.id_alert_owner))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		return true;
	}


}
