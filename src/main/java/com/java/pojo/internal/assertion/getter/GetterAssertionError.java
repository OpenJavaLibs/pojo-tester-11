package com.java.pojo.internal.assertion.getter;

import java.lang.reflect.Field;
import com.java.pojo.internal.assertion.AbstractAssertionError;

class GetterAssertionError extends AbstractAssertionError {

    private static final String CONSTRAINT_GETTER = "The getter method for field '%s' should return field value.\n"
                                                    + "Current implementation returns different value.\n"
                                                    + "Expected value:\n"
                                                    + "%s\n"
                                                    + "but was:\n"
                                                    + "%s";

    private final Field field;
    private final Object expectedValue;
    private final Object currentValue;

    GetterAssertionError(final Class<?> testedCass, final Field field, final Object expectedValue, final Object currentValue) {
        super(testedCass);
        this.field = field;
        this.expectedValue = expectedValue;
        this.currentValue = currentValue;
    }

    @Override
    protected String getDetailedMessage() {
        return String.format(CONSTRAINT_GETTER, field, expectedValue, currentValue);
    }

    @Override
    protected String getErrorPrefix() {
        return String.format("Class %s has bad 'getter' method implementation.", testedCass.getCanonicalName());
    }

}
