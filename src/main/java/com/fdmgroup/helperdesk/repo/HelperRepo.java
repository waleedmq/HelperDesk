package com.fdmgroup.helperdesk.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.helperdesk.model.Helper;

@Repository
public interface HelperRepo extends JpaRepository<Helper, Long>{

	Optional<Helper> findByUsername(String username);
	
	@Query(value = "SELECT * FROM helpers h WHERE h.user_id in (SELECT hp.fk_helper_id FROM helper_planguages hp WHERE hp.fk_planguage_id = :planguage_id)",
			nativeQuery = true)
	List<Helper> getHelpersbypLanguage(@Param("planguage_id") long pLanguageId);

}
