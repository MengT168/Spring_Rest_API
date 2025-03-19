package com.meng.test.API.Service;

import java.util.List;

import com.meng.test.API.Entity.Model;

public interface ModelService {
	Model createModel(Model model);
	List<Model> getModel();
	Model getById(Integer id);
	Model updateModel(Integer id , Model model);
	void delete(Integer id);
}
