package com.eci.innovation.storerun.mapper;

import com.eci.innovation.storerun.domain.Discounts;
import com.eci.innovation.storerun.dto.DiscountsDTO;

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
public interface DiscountsMapper {
    @Mapping(source = "items.itemId", target = "itemId_Items")
    public DiscountsDTO discountsToDiscountsDTO(Discounts discounts)
        throws Exception;

    @Mapping(source = "itemId_Items", target = "items.itemId")
    public Discounts discountsDTOToDiscounts(DiscountsDTO discountsDTO)
        throws Exception;

    public List<DiscountsDTO> listDiscountsToListDiscountsDTO(
        List<Discounts> discountss) throws Exception;

    public List<Discounts> listDiscountsDTOToListDiscounts(
        List<DiscountsDTO> discountsDTOs) throws Exception;
}
