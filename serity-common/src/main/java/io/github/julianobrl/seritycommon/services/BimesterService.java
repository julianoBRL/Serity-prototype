package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Bimester;
import io.github.julianobrl.seritycommon.repositorys.BimesterRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class BimesterService {
	
	@Autowired
	@SuppressWarnings("unused")
	private BimesterRepository repository;
	
	public Iterable<Bimester> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Bimester getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Bimester save(Bimester model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Bimester edit(Long iD, Bimester model) {
		Bimester savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Bimester> list(Bimester params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
