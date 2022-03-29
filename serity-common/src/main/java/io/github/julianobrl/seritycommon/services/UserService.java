package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.User;
import io.github.julianobrl.seritycommon.repositorys.UserRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class UserService {
	
	@Autowired
	@SuppressWarnings("unused")
	private UserRepository repository;
	
	public Iterable<User> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public User getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public User save(User model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public User edit(Long iD, User model) {
		User savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<User> list(User params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
