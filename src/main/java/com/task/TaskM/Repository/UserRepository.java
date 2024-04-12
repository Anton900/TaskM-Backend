package com.task.TaskM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.TaskM.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	/*
	 * Some example of methods that gets inherited
	 * save(T entity): Saves a given entity and returns the saved entity.
	 * findById(ID id): Retrieves an entity by its ID.
	 * findAll(): Returns all instances of the entity.
	 * deleteById(ID id): Deletes the entity with the given ID.
	 * count(): Returns the number of entities.
	 * and many more...
	 */
	
	//can add custom queries if needed
}
