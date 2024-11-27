package com.java.pojo.api;

import com.java.pojo.internal.utils.FieldUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class is used to create field predicates. It has methods that allow to create common predicates e.g. accept all
 * fields.
 *
 * @author Piotr Joński
 * @since 0.1.0
 */
public final class FieldPredicate {

    private FieldPredicate() {
    }

    /**
     * Creates {@link Predicate} that accepts all fields of specified class.
     *
     * @param clazz class, which fields will be accepted
     * @return {@link Predicate} that accepts all fields of given class
     * @see Predicate
     */
    public static Predicate<String> includeAllFields(final Class<?> clazz) {
        final List<String> allFieldNames = FieldUtils.getAllFieldNames(clazz);
        return include(allFieldNames);
    }

    /**
     * Creates {@link Predicate} that accepts given fields.
     *
     * @param includedFields fields, that will be included into predicate
     * @return {@link Predicate} that accepts given fields
     * @see Predicate
     */
    public static Predicate<String> include(final List<String> includedFields) {
        Predicate<String> predicate = getAlwaysFalsePredicate();
        for (final String filedName : includedFields) {
            predicate = predicate.or(getEqualPredicate(filedName));
        }
        return predicate;
    }

    /**
     * Creates {@link Predicate} that accepts given fields.
     *
     * @param includedFields fields, that will be included into predicate
     * @return {@link Predicate} that accepts given fields
     * @see Predicate
     */
    public static Predicate<String> include(final String... includedFields) {
        return include(Arrays.asList(includedFields));
    }


    /**
     * Creates {@link Predicate} that rejects given fields.
     *
     * @param excludedFields fields, that will be excluded from predicate
     * @return {@link Predicate} that rejects given fields
     * @see Predicate
     */
    public static Predicate<String> exclude(final List<String> excludedFields) {
        Predicate<String> predicate = getAlwaysTruePredicate();
        for (final String filedName : excludedFields) {
            predicate = predicate.and(getNonEqualPredicate(filedName));
        }
        return predicate;
    }

    /**
     * Creates {@link Predicate} that rejects given fields.
     *
     * @param excludedFields fields, that will be excluded from predicate
     * @return {@link Predicate} that rejects given fields
     * @see Predicate
     */
    public static Predicate<String> exclude(final String... excludedFields) {
        return exclude(Arrays.asList(excludedFields));
    }

    private static Predicate<String> getAlwaysFalsePredicate() {
        return new NamedPredicate<>(fieldName -> false);
    }

    private static Predicate<String> getAlwaysTruePredicate() {
        return new NamedPredicate<>(fieldName -> true);
    }

    private static Predicate<String> getNonEqualPredicate(final String filedName) {
        final NamedPredicate<String> equalPredicate = getEqualPredicate(filedName);
        return new NamedPredicate<>(equalPredicate.negate());
    }

    private static NamedPredicate<String> getEqualPredicate(final String filedName) {
        return new NamedPredicate<>(filedName, otherFieldName -> otherFieldName.equals(filedName));
    }

}

