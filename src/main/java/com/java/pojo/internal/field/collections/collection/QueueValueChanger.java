package com.java.pojo.internal.field.collections.collection;

import com.java.pojo.internal.utils.CollectionUtils;

import java.util.LinkedList;
import java.util.Queue;

class QueueValueChanger extends AbstractCollectionFieldValueChanger<Queue<?>> {

    @Override
    protected Queue<?> increaseValue(final Queue<?> value, final Class<?> type) {
        return CollectionUtils.isNotEmpty(value)
               ? null
               : new LinkedList<>(CollectionUtils.asList(new Object()));
    }
}
