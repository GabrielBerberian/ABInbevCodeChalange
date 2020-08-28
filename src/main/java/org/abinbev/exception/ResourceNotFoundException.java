package org.abinbev.exception;

import lombok.Getter;

/**
 * Exception to be thrown on resource not found
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final String EXPECTED_RESOURCE_MESSAGE =
            "Resource of type %s not found. Expected resource with %s=%s.";

    @Getter
    private final String message;

    public ResourceNotFoundException(Class clss, String key, String value) {
        String resourceClassName = clss.getSimpleName();
        this.message = String.format(EXPECTED_RESOURCE_MESSAGE, resourceClassName, key, value);
    }
}
