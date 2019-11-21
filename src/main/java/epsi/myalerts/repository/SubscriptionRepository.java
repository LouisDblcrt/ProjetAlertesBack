package epsi.myalerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import epsi.myalerts.model.SubscriptionPK;
import epsi.myalerts.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionPK> {

}
