package epsi.myalerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epsi.myalerts.model.AlertOwner;
@Repository
public interface AlertOwnerRepository extends JpaRepository<AlertOwner, Integer>{

}
