package com.meng.test.API.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.test.API.DTO.ColorDTO;
import com.meng.test.API.Entity.Color;
import com.meng.test.API.Mapper.ColorMapper;
import com.meng.test.API.Service.ColorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/color")
public class ColorController {
	
	private final ColorService colorService;
	
	@PreAuthorize("hasAuthority('color:write')")
	@PostMapping
	public ResponseEntity<?> createColor(@RequestBody ColorDTO dto){
		Color color = ColorMapper.INSTAN.toColor(dto);
		color = colorService.create(color);
		return ResponseEntity.ok(ColorMapper.INSTAN.toDTO(color));
	}
	
	@PreAuthorize("hasAuthority('color:read')")
	@GetMapping
	public ResponseEntity<?> getColors(){
		List<Color> colors = colorService.getColors();
		return ResponseEntity.ok(colors);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Color color = colorService.getById(id);
		return ResponseEntity.ok(ColorMapper.INSTAN.toDTO(color));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateColor(@PathVariable int id ,@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTAN.toColor(colorDTO);
		Color updateColor = colorService.updateColor(id,color );
		return ResponseEntity.ok(ColorMapper.INSTAN.toDTO(updateColor));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteColor(@PathVariable Integer id){
		colorService.Delete(id);
		return ResponseEntity.ok().build();
	}
	
}
