package com.br.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	
}
