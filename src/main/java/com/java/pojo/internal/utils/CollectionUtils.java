package com.java.pojo.internal.utils;


import java.util.*;

public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

    static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> ArrayList<T> asList(final T... elements) {
        final ArrayList<T> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    public static <T> Set<T> asSet(final T... elements) {
        final HashSet<T> list = new HashSet<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }
}
