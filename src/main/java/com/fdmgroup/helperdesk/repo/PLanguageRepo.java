package com.fdmgroup.helperdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.ProgrammingLanguage;
import com.fdmgroup.helperdesk.model.Ticket;

@Repository
public interface PLanguageRepo extends JpaRepository<ProgrammingLanguage, Long>{

}
