package com.java.pojo.internal.tester;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import com.java.pojo.api.FieldPredicate;
import com.java.pojo.internal.field.DefaultFieldValueChanger;
import com.java.pojo.internal.utils.GetterNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.util.Lists.newArrayList;


class GetterTesterTest {

    @Test
    void Should_Pass_All_Getter_Tests() {
        // given
        final Class[] classesToTest = {GoodPojoGetter.class};
        final GetterTester getterTester = new GetterTester(DefaultFieldValueChanger.INSTANCE);

        // when
        final Throwable result = catchThrowable(() -> getterTester.testAll(classesToTest));

        // then
        assertThat(result).isNull();
    }

    @Test
    void Should_Pass_All_Getter_Tests_Excluding_Fields() {
        // given
        final GetterTester getterTester = new GetterTester();
        final Class<?> clazz = BadPojoGetter.class;
        final List<String> excludedFields = newArrayList("c", "d", "charY");

        // when
        final Throwable result = catchThrowable(() -> getterTester.test(clazz, FieldPredicate.exclude(excludedFields)));

        // then
        assertThat(result).isNull();
    }

    @Test
    void Should_Pass_All_Getter_Tests_Including_Fields() {
        // given
        final GetterTester getterTester = new GetterTester();
        final Class<?> clazz = BadPojoGetter.class;
        final List<String> includedFields = newArrayList("a", "b");

        // when
        final Throwable result = catchThrowable(() -> getterTester.test(clazz, FieldPredicate.include(includedFields)));

        // then
        assertThat(result).isNull();
    }

    @Test
    void Should_Fail_Multiple_Classes() {
        // given
        final Class[] classesToTest = {BadPojoGetter.class, Getters.class};
        final GetterTester getterTester = new GetterTester();

        // when
        final Throwable result = catchThrowable(() -> getterTester.testAll(classesToTest));

        // then
        assertThat(result).isInstanceOf(GetterNotFoundException.class);
    }

    @Getter
    private class GoodPojoGetter {
        private int a;
        private int b;
        private int c;
        private int d;
    }

    private class BadPojoGetter {
        public char charY = 'y';
        private int a;
        private int b;
        private int c;
        private int d;

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public char getX() {
            return 'x';
        }
    }

    private class Getters {

        boolean getter1;
        boolean getter2;
        boolean getter3;
        Boolean getter4;
        int getter5;
        Integer getter6;
        Boolean getter7;
        private int a;
        private int b;
        private int d;
        private boolean e;
        private boolean f;
        private boolean g;

        public Integer getGetter6() {
            return getter6;
        }

        public Boolean getGetter7() {
            return getter7;
        }

        public boolean isGetter1() {
            return getter1;
        }

        public boolean hasGetter2() {
            return getter2;
        }

        public boolean haveGetter3() {
            return getter3;
        }

        public Boolean containsGetter4() {
            return getter4;
        }

        public int getGetter5() {
            return getter5;
        }

        public int a() {
            return 0;
        }

        public boolean e() {
            return false;
        }

        public int getB(final Object o) {
            return 0;
        }

        public int get() {
            return 0;
        }

        public boolean issG() {
            return g;
        }
    }

}
