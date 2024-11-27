package com.java.pojo.api;

/**
 * Interface for package filtering.
 *
 * @author Piotr Joński
 * @since 0.5.0
 */
@FunctionalInterface
public interface PackageFilter {

    /**
     * Returns classes filtered by filter.
     *
     * @return classes
     */
    Class<?>[] getClasses();
}
