package io.github.julianobrl.teadentmodule.controllers;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.julianobrl.seritycommon.entitys.ActivityRegistry;
import io.github.julianobrl.seritycommon.services.ActivityRegistryService;
import io.github.julianobrl.seritycommon.utils.GSpecification;

@RestController
@RequestMapping("/activityregistry")
public class ActivityRegistryController {
	
	@Autowired
	private ActivityRegistryService service;
	
	@GetMapping
	private ResponseEntity<?> getAll(@RequestBody(required = false) ActivityRegistry params) {
		Specification<ActivityRegistry> spec = new GSpecification<ActivityRegistry>().setSpecs(params);
		return ResponseEntity.status(HttpStatus.OK).body((PageImpl<?>) service.list(params,spec));
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?> getByID(@PathVariable("id") Long ID) throws Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getByID(ID));
		}catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id <"+ID+"> not found!!!");
		}
	}
	
	@PostMapping
	private ResponseEntity<?> register(@Valid @RequestBody ActivityRegistry model){
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	private ResponseEntity<?> delete(@PathVariable("id") Long ID){
		try {
			service.delete(ID);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id <"+ID+"> not found!!!");
		}
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<?> edit(@PathVariable("id") Long ID, @RequestBody ActivityRegistry model){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.edit(ID,model));
		}catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id <"+ID+"> not found!!!");
		}
	}
	
}
