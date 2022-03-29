package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Test;
import io.github.julianobrl.seritycommon.repositorys.TestRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class TestService {
	
	@Autowired
	@SuppressWarnings("unused")
	private TestRepository repository;
	
	public Iterable<Test> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Test getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Test save(Test model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Test edit(Long iD, Test model) {
		Test savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Test> list(Test params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
