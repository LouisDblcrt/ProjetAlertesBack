package epsi.myalerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epsi.myalerts.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer> {

}
