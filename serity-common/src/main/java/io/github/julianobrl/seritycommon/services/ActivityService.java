package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Activity;
import io.github.julianobrl.seritycommon.repositorys.ActivityRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class ActivityService {
	
	@Autowired
	@SuppressWarnings("unused")
	private ActivityRepository repository;
	
	public Iterable<Activity> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Activity getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Activity save(Activity model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Activity edit(Long iD, Activity model) {
		Activity savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Activity> list(Activity params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
