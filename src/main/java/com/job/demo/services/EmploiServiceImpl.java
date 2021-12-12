package com.job.demo.services;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.demo.entities.Emploi;
import com.job.demo.exceptions.EmploiCollectionException;
import com.job.demo.repositories.EmploiRepository;

@Service
public class EmploiServiceImpl implements EmploiService {

	@Autowired
	private EmploiRepository empRepo;
	
	public void createEmploi(Emploi emploi) throws ConstraintViolationException, EmploiCollectionException{
			Optional<Emploi> empOptional = empRepo.findByEmploi(emploi.getTitre_emploi());
		
				empRepo.save(emploi);
			
		
	}

	
}
