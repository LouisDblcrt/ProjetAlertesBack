package epsi.myalerts.usecase.db;

import org.springframework.data.jpa.repository.JpaRepository;

import epsi.myalerts.domain.entity.Subscription;
import epsi.myalerts.domain.entity.SubscriptionPK;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionPK> {

}
