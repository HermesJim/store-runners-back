package  com.eci.innovation.storerun.service;


import java.math.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.eci.innovation.storerun.exception.*;
import com.eci.innovation.storerun.repository.*;
import com.eci.innovation.storerun.utility.Utilities;

import com.eci.innovation.storerun.domain.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
* 
*/

@Scope("singleton")
@Service
public class ItemsServiceImpl implements ItemsService{

	private static final Logger log = LoggerFactory.getLogger(ItemsServiceImpl.class);

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Items items)throws Exception{		
		 try {
			Set<ConstraintViolation<Items>> constraintViolations =validator.validate(items);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Items> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}
				 throw new Exception(strMessage.toString());
			 }
		 }catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return itemsRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Items> findAll(){
		log.debug("finding all Items instances");
       	return itemsRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Items save(Items entity) throws Exception {
		log.debug("saving Items instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Items");
		}
		
		validate(entity);	
	
		if(itemsRepository.findById(entity.getItemId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return itemsRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Items failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Items entity) throws Exception {
            	log.debug("deleting Items instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Items");
	    		}
    	
                                if(entity.getItemId()==null){
                    throw new ZMessManager().new EmptyFieldException("itemId");
                    }
                        
            	            findById(entity.getItemId()).ifPresent(entidad->{	            	
	                													List<Discounts> discountses = entidad.getDiscountses();
	                    	                    if(Utilities.validationsList(discountses)==true){
                       	 	throw new ZMessManager().new DeletingException("discountses");
                        }
	                	            });
                       

            try {
            
            itemsRepository.deleteById(entity.getItemId());
            log.debug("delete Items successful");
            
            } catch (Exception e) {
            	log.error("delete Items failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Long id) throws Exception {            
            	log.debug("deleting Items instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("itemId");
            	}
            	if(itemsRepository.findById(id).isPresent()){
           			delete(itemsRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Items update(Items entity) throws Exception {

				log.debug("updating Items instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Items");
		    		}
		    		
	            validate(entity);
	
	            return itemsRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Items failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Items> findById(Long itemId) throws Exception {            
            	log.debug("getting Items instance");
            	return itemsRepository.findById(itemId);
            }
			
}
