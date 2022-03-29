package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Ticket;
import io.github.julianobrl.seritycommon.repositorys.TicketRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class TicketService {
	
	@Autowired
	@SuppressWarnings("unused")
	private TicketRepository repository;
	
	public Iterable<Ticket> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Ticket getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Ticket save(Ticket model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Ticket edit(Long iD, Ticket model) {
		Ticket savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Ticket> list(Ticket params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
