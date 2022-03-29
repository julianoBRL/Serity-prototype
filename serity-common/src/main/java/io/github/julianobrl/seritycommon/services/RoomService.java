package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Room;
import io.github.julianobrl.seritycommon.repositorys.RoomRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class RoomService {
	
	@Autowired
	@SuppressWarnings("unused")
	private RoomRepository repository;
	
	public Iterable<Room> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Room getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Room save(Room model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Room edit(Long iD, Room model) {
		Room savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Room> list(Room params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
