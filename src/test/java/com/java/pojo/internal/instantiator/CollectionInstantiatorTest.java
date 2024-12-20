package com.java.pojo.internal.instantiator;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

import static helpers.TestHelper.getDefaultDisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;


class CollectionInstantiatorTest {

    @TestFactory
    Stream<DynamicTest> Should_Return_Expected_Collection_Object() {
        return Stream.of(Stream.class,
                         Collection.class,
                         List.class,
                         Stack.class,
                         Vector.class,
                         ArrayList.class,
                         LinkedList.class,
                         Queue.class,
                         Deque.class,
                         Set.class,
                         HashSet.class,
                         LinkedHashSet.class,
                         SortedSet.class,
                         NavigableSet.class,
                         TreeSet.class,
                         Iterator.class,
                         Iterable.class,
                         Map.class,
                         HashMap.class,
                         LinkedHashMap.class,
                         Hashtable.class,
                         SortedMap.class,
                         NavigableMap.class,
                         TreeMap.class)
                     .map(value -> dynamicTest(getDefaultDisplayName(value),
                                               Should_Return_Expected_Collection_Object(value)));
    }

    private Executable Should_Return_Expected_Collection_Object(final Class<?> classToInstantiate) {
        return () -> {
            // given
            final CollectionInstantiator instantiator = new CollectionInstantiator(classToInstantiate,
                                                                                   new ArrayListValuedHashMap<>());

            // when
            final Object result = instantiator.instantiate();

            // then
            assertThat(result).isInstanceOf(classToInstantiate);
        };
    }

    @Test
    void Should_Throws_Exception_When_Prepared_Objects_Do_Not_Contain_Expected_Class() {
        // given
        final CollectionInstantiator instantiator = new CollectionInstantiator(String.class,
                                                                               new ArrayListValuedHashMap<>());

        // when
        final Throwable result = catchThrowable(instantiator::instantiate);

        // then
        assertThat(result).isInstanceOf(ObjectInstantiationException.class);
    }

}
