package com.meng.test.API.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.meng.test.API.DTO.ProductDTO;
import com.meng.test.API.DTO.ProductImportDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Entity.ProductImportHistory;
import com.meng.test.API.Service.ColorService;
import com.meng.test.API.Service.ModelService;

@Mapper(componentModel = "spring" , uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
	
	@Mapping(target = "model" , source = "modelId")
	@Mapping(target = "color" , source = "colorId")
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "salePrice", ignore = true)
    @Mapping(target = "availableUnit", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
	Product toProduct(ProductDTO dto);
	
	
	@Mapping(target = "date_import" , source = "importDTO.importDate")
	@Mapping(target = "import_unit" , source = "importDTO.importUnit")
	@Mapping(target = "price_per_unit" , source = "importDTO.importPrice")
	@Mapping(target = "id", ignore = true)
	ProductImportHistory productImportHistory(ProductImportDTO importDTO , Product product);
	
	
}
