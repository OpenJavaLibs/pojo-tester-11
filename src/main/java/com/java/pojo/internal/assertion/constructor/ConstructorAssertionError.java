package com.java.pojo.internal.assertion.constructor;

import com.java.pojo.internal.assertion.AbstractAssertionError;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Collectors;


public class ConstructorAssertionError extends AbstractAssertionError {

    private static final String INSTANTIATE_EXCEPTION = "Constructor:\n"
                                                        + "%s\n"
                                                        + "of class:\n"
                                                        + "%s\n"
                                                        + "could not create instance with parameters:\n"
                                                        + "%s\n"
                                                        + "Root cause is:\n"
                                                        + "%s";
    private final Constructor<?> constructorUnderAssert;
    private final Object[] constructorParameters;
    private final ReflectiveOperationException cause;

    ConstructorAssertionError(final Class<?> classUnderTest,
                              final Constructor<?> constructorUnderAssert,
                              final Object[] constructorParameters,
                              final ReflectiveOperationException cause) {
        super(classUnderTest);
        this.constructorUnderAssert = constructorUnderAssert;
        this.cause = cause;
        this.constructorParameters = constructorParameters == null
                                     ? null
                                     : Arrays.copyOf(constructorParameters, constructorParameters.length);
    }

    @Override
    protected String getErrorPrefix() {
        return String.format("Class %s has bad 'constructor' method implementation.", testedCass.getCanonicalName());
    }

    @Override
    protected String getDetailedMessage() {
        return String.format(INSTANTIATE_EXCEPTION,
                             constructorUnderAssert,
                             testedCass,
                             createArrayContentString(constructorParameters),
                             cause.getMessage());
    }

    private String createArrayContentString(final Object... array) {
        if (array == null) {
            return "<no parameters>";
        }
        return Arrays.stream(array)
                     .map(String::valueOf)
                     .collect(Collectors.joining(", ", "[ ", " ]"));
    }
}
