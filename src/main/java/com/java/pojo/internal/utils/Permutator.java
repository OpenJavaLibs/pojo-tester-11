package com.java.pojo.internal.utils;

import java.lang.reflect.Field;
import java.util.List;

public interface Permutator {

    List<List<Field>> permute(final List<Field> elements);
}
