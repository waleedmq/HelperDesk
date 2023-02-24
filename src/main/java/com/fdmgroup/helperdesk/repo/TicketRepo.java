package com.fdmgroup.helperdesk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;

import jakarta.persistence.criteria.From;
import jakarta.transaction.Transactional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
	
	@Query(value = "SELECT * FROM tickets t WHERE t.fk_trainee_id = :trainee_id AND t.status = 'Open'",
			nativeQuery = true)
	List<Ticket> getOpenTraineeTicketsById(@Param("trainee_id") long traineeId);

	@Query(value = "SELECT * FROM tickets t WHERE t.fk_helper_id = :helper_id AND t.status = 'Open'",
			nativeQuery = true)
	List<Ticket> getOpenHelperTicketsById(@Param("helper_id") long helperId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tickets t SET t.status = 'Closed' WHERE t.ticket_id = :ticket_Id",
			nativeQuery = true)
	void closeTicketbyId(@Param("ticket_Id") long ticketId);
	
}
