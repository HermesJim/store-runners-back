package com.eci.innovation.storerun.mapper;

import com.eci.innovation.storerun.domain.ShoppingCar;
import com.eci.innovation.storerun.dto.ShoppingCarDTO;

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
public interface ShoppingCarMapper {
	@Mapping(source = "categories.categoryId", target = "shop_categoryId")
    public ShoppingCarDTO shoppingCarToShoppingCarDTO(ShoppingCar shoppingCar)
        throws Exception;

    @Mapping(source = "shop_categoryId", target = "categories.categoryId")
    public ShoppingCar shoppingCarDTOToShoppingCar(
        ShoppingCarDTO shoppingCarDTO) throws Exception;

    public List<ShoppingCarDTO> listShoppingCarToListShoppingCarDTO(
        List<ShoppingCar> shoppingCars) throws Exception;

    public List<ShoppingCar> listShoppingCarDTOToListShoppingCar(
        List<ShoppingCarDTO> shoppingCarDTOs) throws Exception;
}
