package org.modelmapperexercise.util;

import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public interface ValidatorService {
   <E> boolean isValid(E entity);

  <E> Set<ConstraintViolationException>validate(E entity);
}
