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
public class CategoriesServiceImpl implements CategoriesService{

	private static final Logger log = LoggerFactory.getLogger(CategoriesServiceImpl.class);

	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Categories categories)throws Exception{		
		 try {
			Set<ConstraintViolation<Categories>> constraintViolations =validator.validate(categories);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Categories> constraintViolation : constraintViolations) {
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
	 	return categoriesRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Categories> findAll(){
		log.debug("finding all Categories instances");
       	return categoriesRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Categories save(Categories entity) throws Exception {
		log.debug("saving Categories instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Categories");
		}
		
		validate(entity);	
	
		if(categoriesRepository.findById(entity.getCategoryId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return categoriesRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Categories failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Categories entity) throws Exception {
            	log.debug("deleting Categories instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Categories");
	    		}
    	
                                if(entity.getCategoryId()==null){
                    throw new ZMessManager().new EmptyFieldException("categoryId");
                    }
                        
            	            findById(entity.getCategoryId()).ifPresent(entidad->{	            	
	                													List<Items> itemses = entidad.getItemses();
	                    	                    if(Utilities.validationsList(itemses)==true){
                       	 	throw new ZMessManager().new DeletingException("itemses");
                        }
	                	            });
                       

            try {
            
            categoriesRepository.deleteById(entity.getCategoryId());
            log.debug("delete Categories successful");
            
            } catch (Exception e) {
            	log.error("delete Categories failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Long id) throws Exception {            
            	log.debug("deleting Categories instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("categoryId");
            	}
            	if(categoriesRepository.findById(id).isPresent()){
           			delete(categoriesRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Categories update(Categories entity) throws Exception {

				log.debug("updating Categories instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Categories");
		    		}
		    		
	            validate(entity);
	
	            return categoriesRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Categories failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Categories> findById(Long categoryId) throws Exception {            
            	log.debug("getting Categories instance");
            	return categoriesRepository.findById(categoryId);
            }
			
}
