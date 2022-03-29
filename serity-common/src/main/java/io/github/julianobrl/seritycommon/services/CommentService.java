package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Comment;
import io.github.julianobrl.seritycommon.repositorys.CommentRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class CommentService {
	
	@Autowired
	@SuppressWarnings("unused")
	private CommentRepository repository;
	
	public Iterable<Comment> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Comment getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Comment save(Comment model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Comment edit(Long iD, Comment model) {
		Comment savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<Comment> list(Comment params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
