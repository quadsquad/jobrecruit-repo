package com.job.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.demo.entities.Emploi;
import com.job.demo.exceptions.EmploiCollectionException;
import com.job.demo.repositories.EmploiRepository;
import com.job.demo.services.EmploiService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmploiController{

@Autowired
private EmploiRepository empRepo;

@Autowired
private EmploiService empService;


@GetMapping("/findAllEmplois")
public ResponseEntity<?> getAllEmplois(){
List<Emploi> emps = empRepo.findAll();
if(emps.size() > 0){
return new ResponseEntity<List<Emploi>>(emps, HttpStatus.OK);

}
else
{
return new ResponseEntity<String>("No emplois Available",HttpStatus.NOT_FOUND);
}
}



@PostMapping("/emplois")
public ResponseEntity<?> createEmploi(@RequestBody Emploi emploi){
try {
empService.createEmploi(emploi);
return new ResponseEntity<Emploi>(emploi, HttpStatus.OK);
} catch (ConstraintViolationException e) {
// TODO: handle exception
return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
}catch(EmploiCollectionException e){
return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

}
}

@GetMapping("/emplois/{category}")
public ResponseEntity<?> getSingleEmploi(@PathVariable("category") String category){
Optional<Emploi> emploiOptional = empRepo.findByEmploi(category);
if (emploiOptional.isPresent())
{
return new ResponseEntity<Emploi>(emploiOptional.get(), HttpStatus.OK);
}else{
return new ResponseEntity<String>("Emploi not found", HttpStatus.NOT_FOUND);
}
}

@PutMapping("/emplois/{id}")
public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody Emploi emploi){
Optional<Emploi> emploiOptional = empRepo.findById(id);
if (emploiOptional.isPresent())
{
Emploi emploiToSave = emploiOptional.get();
emploiToSave.setTitre_emploi(emploi.getTitre_emploi() != null? emploi.getTitre_emploi(): emploiToSave.getTitre_emploi());
emploiToSave.setDescription(emploi.getDescription() != null? emploi.getDescription(): emploiToSave.getDescription());
emploiToSave.setAdresse(emploi.getAdresse() != null? emploi.getAdresse(): emploiToSave.getAdresse());
emploiToSave.setNom_entreprise(emploi.getNom_entreprise() != null? emploi.getNom_entreprise(): emploiToSave.getNom_entreprise());
emploiToSave.setType_emploi(emploi.getType_emploi() != null? emploi.getType_emploi(): emploiToSave.getType_emploi());
emploiToSave.setStatus(emploi.getStatus() != null? emploi.getStatus(): emploiToSave.getStatus());
emploiToSave.setCategory(emploi.getCategory() != null? emploi.getCategory(): emploiToSave.getCategory());

empRepo.save(emploiToSave);
return new ResponseEntity<Emploi>(emploiOptional.get(), HttpStatus.OK);

}else{
return new ResponseEntity<String>("Emploi not found", HttpStatus.NOT_FOUND);
}
}

//@RequestBody ObjectName

@DeleteMapping("/emplois/{id}")
public ResponseEntity<?> deleteEmploi(@PathVariable("id") String id){
try{
empRepo.deleteById(id);
return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
}catch(Exception e){
return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}


}