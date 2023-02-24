package com.fdmgroup.helperdesk.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;

@Repository
public interface TraineeRepo extends JpaRepository<Trainee, Long>{

	Optional<Trainee> findByUsername(String username);

}
