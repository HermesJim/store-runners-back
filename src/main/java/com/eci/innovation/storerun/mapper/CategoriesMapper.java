package com.eci.innovation.storerun.mapper;

import com.eci.innovation.storerun.domain.Categories;
import com.eci.innovation.storerun.dto.CategoriesDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface CategoriesMapper {
    public CategoriesDTO categoriesToCategoriesDTO(Categories categories)
        throws Exception;

    public Categories categoriesDTOToCategories(CategoriesDTO categoriesDTO)
        throws Exception;

    public List<CategoriesDTO> listCategoriesToListCategoriesDTO(
        List<Categories> categoriess) throws Exception;

    public List<Categories> listCategoriesDTOToListCategories(
        List<CategoriesDTO> categoriesDTOs) throws Exception;
}
