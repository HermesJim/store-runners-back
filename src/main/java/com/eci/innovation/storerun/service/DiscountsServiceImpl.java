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
public class DiscountsServiceImpl implements DiscountsService {
    private static final Logger log = LoggerFactory.getLogger(DiscountsServiceImpl.class);
    @Autowired
    private DiscountsRepository discountsRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(Discounts discounts) throws Exception {
        try {
            Set<ConstraintViolation<Discounts>> constraintViolations = validator.validate(discounts);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Discounts> constraintViolation : constraintViolations) {
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
        return discountsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Discounts> findAll() {
        log.debug("finding all Discounts instances");

        return discountsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Discounts save(Discounts entity) throws Exception {
        log.debug("saving Discounts instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Discounts");
            }

            validate(entity);

            if (discountsRepository.findById(entity.getDiscountId()).isPresent()) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            return discountsRepository.save(entity);
        } catch (Exception e) {
            log.error("save Discounts failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Discounts entity) throws Exception {
        log.debug("deleting Discounts instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Discounts");
        }

        if (entity.getDiscountId() == null) {
            throw new ZMessManager().new EmptyFieldException("discountId");
        }

        try {
            discountsRepository.deleteById(entity.getDiscountId());
            log.debug("delete Discounts successful");
        } catch (Exception e) {
            log.error("delete Discounts failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws Exception {
        log.debug("deleting Discounts instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("discountId");
        }

        if (discountsRepository.findById(id).isPresent()) {
            delete(discountsRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Discounts update(Discounts entity) throws Exception {
        log.debug("updating Discounts instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Discounts");
            }

            validate(entity);

            return discountsRepository.save(entity);
        } catch (Exception e) {
            log.error("update Discounts failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Discounts> findById(Long discountId)
        throws Exception {
        log.debug("getting Discounts instance");

        return discountsRepository.findById(discountId);
    }
}
