package com.meng.test.API.Service;

import java.util.List;

import com.meng.test.API.Entity.Color;

public interface ColorService {
	Color create (Color color);
	List<Color> getColors();
	Color getById(Integer id);
	Color updateColor(Integer id , Color color);
	void Delete(Integer id);
}
