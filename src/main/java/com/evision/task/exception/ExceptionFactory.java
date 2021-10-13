/**
 * 
 */
package com.evision.task.exception;

import org.springframework.stereotype.Component;

/**
 * @author Mohamed Ramadan
 *
 */
@Component
public class ExceptionFactory {


    private ExceptionFactory() {

    }


	public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }
    

}
