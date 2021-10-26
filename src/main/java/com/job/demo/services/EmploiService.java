package com.job.demo.services;

import org.hibernate.exception.ConstraintViolationException;

import com.job.demo.entities.Emploi;
import com.job.demo.exceptions.EmploiCollectionException;

public interface EmploiService {

	public void createEmploi(Emploi emploi) throws ConstraintViolationException, EmploiCollectionException;
	
}