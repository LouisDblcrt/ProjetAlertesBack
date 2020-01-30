package epsi.myalerts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import epsi.myalerts.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	

	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> selectUserByEmail(String email);
	
	@Query(value="SELECT MAX(id) from User", nativeQuery=true)
	Integer maxIdUser();
}
