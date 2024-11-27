package com.java.pojo.internal.field.collections.collection;

import com.java.pojo.internal.utils.CollectionUtils;

import java.util.Stack;

class StackValueChanger extends AbstractCollectionFieldValueChanger<Stack<?>> {

    @Override
    protected Stack<?> increaseValue(final Stack<?> value, final Class<?> type) {
        return CollectionUtils.isNotEmpty(value)
               ? null
               : createStackWithOneElement();
    }

    private Stack<Object> createStackWithOneElement() {
        final Stack<Object> objects = new Stack<>();
        objects.add(new Object());
        return objects;
    }
}
