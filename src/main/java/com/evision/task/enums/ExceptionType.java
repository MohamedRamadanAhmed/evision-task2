/**
 * 
 */
package com.evision.task.enums;

/**
 * @author Mohamed Ramadan
 *
 */
public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    ENTITY_EXCEPTION("exception"),
	DUPLICATE_ENTITY("duplicate.entity");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
