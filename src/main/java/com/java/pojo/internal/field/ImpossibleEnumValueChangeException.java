package com.java.pojo.internal.field;

class ImpossibleEnumValueChangeException extends RuntimeException {

    ImpossibleEnumValueChangeException(final Class<?> type) {
        super("Enum with type '" + type.getName() + "' has no enum constants. The only value of field with this type is null.");
    }
}
