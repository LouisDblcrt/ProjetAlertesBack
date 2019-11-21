package epsi.myalerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import epsi.myalerts.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
}
