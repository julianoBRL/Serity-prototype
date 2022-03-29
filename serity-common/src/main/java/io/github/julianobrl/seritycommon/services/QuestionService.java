package io.github.julianobrl.seritycommon.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.julianobrl.seritycommon.entitys.Question;
import io.github.julianobrl.seritycommon.repositorys.QuestionRepository;
import io.github.julianobrl.seritycommon.utils.PropertyFilter;

@Service
public class QuestionService {
	
	@Autowired
	@SuppressWarnings("unused")
	private QuestionRepository repository;
	
	public Iterable<Question> list() {
		return repository.findAll(PageRequest.of(0, 10));
	}
	
	public Question getByID(Long iD){
		return repository.findById(iD).get();
	}
	
	public Question save(Question model) {
		return repository.save(model);
	}
	
	public void delete(Long iD) {
		repository.deleteById(iD);
	}
	
	public Question edit(Long iD, Question model) {
		Question savedData = getByID(iD);
		BeanUtils.copyProperties(model, savedData, PropertyFilter.getNullPropertyNames(model));
		return repository.save(savedData);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Question> list(Question params, Specification spec) {
		return repository.findAll(spec,PageRequest.of(0, 10));
	}
	
}
