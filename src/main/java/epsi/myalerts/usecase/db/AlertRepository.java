package epsi.myalerts.usecase.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epsi.myalerts.domain.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer> {

}
