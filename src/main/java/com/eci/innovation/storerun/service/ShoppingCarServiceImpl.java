package com.eci.innovation.storerun.service;

import com.eci.innovation.storerun.domain.*;
import com.eci.innovation.storerun.exception.*;
import com.eci.innovation.storerun.repository.*;
import com.eci.innovation.storerun.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCarServiceImpl.class);
    @Autowired
    private ShoppingCarRepository shoppingCarRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(ShoppingCar shoppingCar) throws Exception {
        try {
            Set<ConstraintViolation<ShoppingCar>> constraintViolations = validator.validate(shoppingCar);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<ShoppingCar> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return shoppingCarRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCar> findAll() {
        log.debug("finding all ShoppingCar instances");

        return shoppingCarRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ShoppingCar save(ShoppingCar entity) throws Exception {
        log.debug("saving ShoppingCar instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("ShoppingCar");
            }

            validate(entity);

            if (shoppingCarRepository.findById(entity.getCartId()).isPresent()) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            return shoppingCarRepository.save(entity);
        } catch (Exception e) {
            log.error("save ShoppingCar failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(ShoppingCar entity) throws Exception {
        log.debug("deleting ShoppingCar instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("ShoppingCar");
        }

        if (entity.getCartId() == null) {
            throw new ZMessManager().new EmptyFieldException("cartId");
        }

        try {
            shoppingCarRepository.deleteById(entity.getCartId());
            log.debug("delete ShoppingCar successful");
        } catch (Exception e) {
            log.error("delete ShoppingCar failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws Exception {
        log.debug("deleting ShoppingCar instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("cartId");
        }

        if (shoppingCarRepository.findById(id).isPresent()) {
            delete(shoppingCarRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ShoppingCar update(ShoppingCar entity) throws Exception {
        log.debug("updating ShoppingCar instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("ShoppingCar");
            }

            validate(entity);

            return shoppingCarRepository.save(entity);
        } catch (Exception e) {
            log.error("update ShoppingCar failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCar> findById(Long cartId)
        throws Exception {
        log.debug("getting ShoppingCar instance");

        return shoppingCarRepository.findById(cartId);
    }
}
