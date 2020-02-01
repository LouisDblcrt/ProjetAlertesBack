package epsi.myalerts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import epsi.myalerts.model.AlertOwner;

@Repository
public interface AlertOwnerRepository extends JpaRepository<AlertOwner, Integer> {

	@Query(value = "SELECT * FROM alert_owner where id not in (SELECT id_alert_owner FROM db_myalerts.subscription where id_user=?)", nativeQuery = true)
	public List<AlertOwner> getAlertsOwnerWithoutSub(Integer id);

	@Query(value = "SELECT * FROM alert_owner where id in (SELECT id_alert_owner FROM db_myalerts.subscription where id_user=?)", nativeQuery = true)
	public List<AlertOwner> getAlertsOwnerWithSub(Integer id);
	

}
