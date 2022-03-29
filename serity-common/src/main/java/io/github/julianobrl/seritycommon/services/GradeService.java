package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Grade;
import io.github.julianobrl.seritycommon.repositorys.GradeRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class GradeService {
	
	@Autowired
	@SuppressWarnings("unused")
	private GradeRepository repository;
	
	public Iterable<Grade> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Grade getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Grade save(Grade model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Grade edit(Long iD, Grade model) {
		Grade savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Grade> list(Grade params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
