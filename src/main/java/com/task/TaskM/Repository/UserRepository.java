package com.task.TaskM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.task.TaskM.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	Optional<User> findByEmail(String email);
	
}
