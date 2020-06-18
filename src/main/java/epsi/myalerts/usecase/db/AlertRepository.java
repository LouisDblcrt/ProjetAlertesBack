package epsi.myalerts.usecase.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import epsi.myalerts.domain.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer> {
	@Query(value="SELECT * from Alert where alert_owner in (select id_alert_owner from Subscription where id_user =?) order by alert_date DESC", nativeQuery=true)
	List<Alert> selectAlertFromUser(Integer id_user);
}
