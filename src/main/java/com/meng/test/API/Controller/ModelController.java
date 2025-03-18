package com.meng.test.API.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.ModelDTO;
import com.meng.test.API.Entity.Model;
import com.meng.test.API.Mapper.ModelEntityMapper;
import com.meng.test.API.Service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model")
public class ModelController {
	
	private final ModelService modelService;
	private final ModelEntityMapper entityMapper;
	
	@PostMapping
	public ResponseEntity<?> createModel(@RequestBody ModelDTO dto){
		Model model = entityMapper.toModel(dto);
		model = modelService.createModel(model);
		return ResponseEntity.ok(entityMapper.toDTO(model));
	}
	
}
