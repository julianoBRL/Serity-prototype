package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Subject;
import io.github.julianobrl.seritycommon.repositorys.SubjectRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class SubjectService {
	
	@Autowired
	@SuppressWarnings("unused")
	private SubjectRepository repository;
	
	public Iterable<Subject> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Subject getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Subject save(Subject model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Subject edit(Long iD, Subject model) {
		Subject savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Subject> list(Subject params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
