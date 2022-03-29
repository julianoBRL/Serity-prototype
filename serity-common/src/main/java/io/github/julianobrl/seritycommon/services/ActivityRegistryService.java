package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.ActivityRegistry;
import io.github.julianobrl.seritycommon.repositorys.ActivityRegistryRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class ActivityRegistryService {
	
	@Autowired
	@SuppressWarnings("unused")
	private ActivityRegistryRepository repository;
	
	public Iterable<ActivityRegistry> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public ActivityRegistry getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public ActivityRegistry save(ActivityRegistry model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public ActivityRegistry edit(Long iD, ActivityRegistry model) {
		ActivityRegistry savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<ActivityRegistry> list(ActivityRegistry params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
