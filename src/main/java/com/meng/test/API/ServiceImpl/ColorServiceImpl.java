package com.meng.test.API.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.Entity.Color;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.ColorRepository;
import com.meng.test.API.Service.ColorService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

	private final ColorRepository colorRepository;
	
	@Override
	public Color create(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public List<Color> getColors() {
		return colorRepository.findAll();
	}

	@Override
	public Color getById(Integer id) {
		return colorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Color", id));
	}

	@Override
	public Color updateColor(Integer id, Color color) {
		Color colors = getById(id);
		colors.setName(color.getName());
		return colorRepository.save(colors);
	}

	@Override
	public void Delete(Integer id) {
		colorRepository.deleteById(id);
	}

}
