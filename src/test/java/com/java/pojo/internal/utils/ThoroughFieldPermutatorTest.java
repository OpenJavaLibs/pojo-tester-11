package com.java.pojo.internal.utils;

import org.junit.jupiter.api.Test;

import artefact.classes.permutator.A;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.java.pojo.internal.utils.CollectionUtils.asList;

class ThoroughFieldPermutatorTest {

    @Test
    void Should_Create_Permutations() throws NoSuchFieldException {
        // given
        final ThoroughFieldPermutator permutator = new ThoroughFieldPermutator();
        final Field aField = A.class.getDeclaredField("a");
        final Field bField = A.class.getDeclaredField("b");
        final List<Field> elements = asList(aField, bField);
        final List<List<Field>> expectedResult = asList(asList(aField), asList(bField), asList(aField, bField));

        // when
        final List<List<Field>> result = permutator.permute(elements);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

}