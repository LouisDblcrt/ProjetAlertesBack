package epsi.myalerts.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="subscription")
public class Subscription {

	@EmbeddedId
	private SubscriptionPK subscriptionPK;
	
	@NotNull
	private Date date_subscription;
	
	

	public SubscriptionPK getSubscriptionPK() {
		return subscriptionPK;
	}

	public void setSubscriptionPK(SubscriptionPK subscriptionPK) {
		this.subscriptionPK = subscriptionPK;
	}

	public Date getDate_subscription() {
		return date_subscription;
	}

	public void setDate_subscription(Date date_subscription) {
		this.date_subscription = date_subscription;
	}

}
