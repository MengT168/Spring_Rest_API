package com.meng.test.API.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<?> getModel(){
		List<Model> model = modelService.getModel();
		return ResponseEntity.ok(model);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getModelById(@PathVariable Integer id){
		Model model = modelService.getById(id);
		return ResponseEntity.ok(entityMapper.toDTO(model));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateModel(@PathVariable Integer id , @RequestBody ModelDTO dto ){
		Model updateModel = modelService.updateModel(id, entityMapper.toModel(dto));
		return ResponseEntity.ok(entityMapper.toDTO(updateModel));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteModel(@PathVariable Integer id){
		modelService.delete(id);
		return ResponseEntity.ok().build();
	}
}
