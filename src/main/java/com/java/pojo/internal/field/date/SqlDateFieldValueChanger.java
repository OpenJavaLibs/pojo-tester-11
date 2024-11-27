package com.java.pojo.internal.field.date;

import com.java.pojo.internal.field.AbstractFieldValueChanger;

import java.sql.Date;

class SqlDateFieldValueChanger extends AbstractFieldValueChanger<Date> {

    @Override
    protected Date increaseValue(final Date value, final Class<?> type) {
        return new Date(value.getTime() + 1000);
    }

    @Override
    protected boolean canChange(final Class<?> type) {
        return type.equals(Date.class);
    }
}
