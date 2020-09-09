package com.eci.innovation.storerun.mapper;

import com.eci.innovation.storerun.domain.Items;
import com.eci.innovation.storerun.dto.ItemsDTO;

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
public interface ItemsMapper {
    @Mapping(source = "categories.categoryId", target = "categoryId_Categories")
    public ItemsDTO itemsToItemsDTO(Items items) throws Exception;

    @Mapping(source = "categoryId_Categories", target = "categories.categoryId")
    public Items itemsDTOToItems(ItemsDTO itemsDTO) throws Exception;

    public List<ItemsDTO> listItemsToListItemsDTO(List<Items> itemss)
        throws Exception;

    public List<Items> listItemsDTOToListItems(List<ItemsDTO> itemsDTOs)
        throws Exception;
}
