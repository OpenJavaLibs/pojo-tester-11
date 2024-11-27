package com.java.pojo.internal.assertion.equals;


class NullEqualsAssertionError extends AbstractEqualsAssertionError {

    private static final String CONSTRAINT_NULL = "The equals method should return false if object is comparing to null.\n"
                                                  + "Current implementation returns true.";

    NullEqualsAssertionError(final Class<?> testedCass) {
        super(testedCass);
    }

    @Override
    protected String getDetailedMessage() {
        return CONSTRAINT_NULL;
    }
}
