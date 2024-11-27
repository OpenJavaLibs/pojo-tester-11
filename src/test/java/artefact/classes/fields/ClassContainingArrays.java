package artefact.classes.fields;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ClassContainingArrays {

    private int[] a_int;
    private char[] a_char;
    private float[] a_float;
    private double[] a_double;
    private boolean[] a_boolean;
    private byte[] a_byte;
    private short[] a_short;
    private long[] a_long;
    private Integer[] a_Int;
    private Character[] a_Char;
    private Float[] a_Float;
    private Double[] a_Double;
    private Boolean[] a_Boolean;
    private Byte[] a_Byte;
    private Short[] a_Short;
    private Long[] a_Long;
    private Object[] a_object_null;
    private Object[] a_object = new Object[1];
    private A a;
    private A[] a_a;

    private class A {}
}
