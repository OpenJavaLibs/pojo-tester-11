package com.java.pojo.internal.field.date;

import com.java.pojo.internal.field.AbstractFieldValueChanger;

import java.time.LocalDateTime;

class LocalDateTimeFieldValueChanger extends AbstractFieldValueChanger<LocalDateTime> {

    @Override
    protected LocalDateTime increaseValue(final LocalDateTime value, final Class<?> type) {
        return value.plusDays(1);
    }

    @Override
    protected boolean canChange(final Class<?> type) {
        return type.equals(LocalDateTime.class);
    }
}
