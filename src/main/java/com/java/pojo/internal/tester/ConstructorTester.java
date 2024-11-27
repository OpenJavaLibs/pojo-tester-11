package com.java.pojo.internal.tester;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.java.pojo.api.ClassAndFieldPredicatePair;
import com.java.pojo.api.ConstructorParameters;
import com.java.pojo.internal.field.AbstractFieldValueChanger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConstructorTester extends AbstractTester {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstructorTester.class);

    public ConstructorTester() {
        super();
    }

    public ConstructorTester(final AbstractFieldValueChanger abstractFieldValueChanger) {
        super(abstractFieldValueChanger);
    }

    @Override
    public void test(final ClassAndFieldPredicatePair baseClassAndFieldPredicatePair, final ClassAndFieldPredicatePair... classAndFieldPredicatePairs) {
        final Class<?> testedClass = baseClassAndFieldPredicatePair.getClazz();
        if (isAbstract(testedClass)) {
            LOGGER.info("Tried to test constructor in abstract ({}) class, annotation or interface. "
                        + "Skipping due to nature of constructors in those classes", testedClass);
            return;
        }
        final List<Constructor<?>> declaredConstructors = getNotSyntheticConstructorFromClass(testedClass);

        declaredConstructors.forEach(this::tryInstantiate);
    }

    private List<Constructor<?>> getNotSyntheticConstructorFromClass(final Class<?> testedClass) {
        return Arrays.stream(testedClass.getDeclaredConstructors())
                     .filter(this::isNotSynthetic)
                     .collect(Collectors.toList());
    }

    private boolean isNotSynthetic(final Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }

    private void tryInstantiate(final Constructor<?> constructor) {
        final Object[] parameters;
        final Predicate<ConstructorParameters> matchingConstructorParameterTypes = ctr -> ctr.matches(constructor.getParameterTypes());

        if (constructorParametersAreProvided(constructor)) {
            final Collection<ConstructorParameters> constructorParameters = getConstructorParameters(constructor);
            parameters = constructorParameters.stream()
                                              .filter(matchingConstructorParameterTypes)
                                              .map(ConstructorParameters::getParameters)
                                              .findFirst()
                                              .orElseGet(() -> logAndTryToCreateOwnParameters(constructor));
        } else {
            parameters = createConstructorParameters(constructor);
        }

        testAssertions.assertThatConstructor(constructor)
                      .willInstantiateClassUsing(parameters);
    }

    private Object[] logAndTryToCreateOwnParameters(final Constructor<?> constructor) {
        LOGGER.warn("Class '{}' could not be created by constructor '{}' and any user defined parameters.",
                    constructor.getDeclaringClass(),
                    constructor);
        return createConstructorParameters(constructor);
    }

    private Collection<ConstructorParameters> getConstructorParameters(final Constructor<?> constructor) {
        return getConstructorParameters().get(constructor.getDeclaringClass());
    }

    private boolean constructorParametersAreProvided(final Constructor<?> constructor) {
        final Class<?> declaringClass = constructor.getDeclaringClass();
        return getConstructorParameters().containsKey(declaringClass);
    }

    private Object[] createConstructorParameters(final Constructor<?> constructor) {
        return Arrays.stream(constructor.getParameterTypes())
                     .map(objectGenerator::createNewInstance)
                     .toArray();
    }

    private boolean isAbstract(final Class<?> clazz) {
        return clazz.isInterface() || clazz.isAnnotation() || Modifier.isAbstract(clazz.getModifiers());
    }
}
