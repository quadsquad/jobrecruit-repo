package com.job.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.job.demo.entities.Emploi;

@Repository
public interface EmploiRepository extends MongoRepository<Emploi, String> {

	@Query("{'emploi': ?0}")
	Optional<Emploi> findByEmploi(String emploi);
}
