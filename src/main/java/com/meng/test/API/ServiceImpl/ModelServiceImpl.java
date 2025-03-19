package com.meng.test.API.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Model;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.ModelRepository;
import com.meng.test.API.Service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;
	
	@Override
	public Model createModel(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getModel() {
		return modelRepository.findAll();
	}

	@Override
	public Model getById(Integer id) {
		Model model = modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model", id));
		return model;
	}

	@Override
	public Model updateModel(Integer id, Model model) {
		Model model1 = getById(id);
		model1.setName(model.getName());
		model1.setBrand(model.getBrand());
		model1 = modelRepository.save(model1);
		return model1;
	}

	@Override
	public void delete(Integer id) {
		modelRepository.deleteById(id);
	}

}
