package com.java.pojo.internal.field.primitive;


class ShortValueChanger extends AbstractPrimitiveValueChanger<Short> {

    @Override
    protected Short increase(final Short value) {
        return (short) (value + 1);
    }
}
