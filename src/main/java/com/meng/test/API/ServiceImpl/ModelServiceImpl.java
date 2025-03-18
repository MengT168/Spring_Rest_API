package com.meng.test.API.ServiceImpl;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Model;
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

}
