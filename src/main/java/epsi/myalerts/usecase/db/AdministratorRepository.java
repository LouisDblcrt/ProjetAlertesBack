package epsi.myalerts.usecase.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epsi.myalerts.domain.entity.Administrator;
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Integer>{


}
