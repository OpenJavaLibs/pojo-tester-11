package artefact.classes.fields;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ClassWithAllAvailableFieldModifiers {

    final static public int m = 1;
    final static protected int n = 1;
    final static int o = 1;
    final static private int p = 1;

    static public int i;
    static protected int j;
    static int k;
    static private int l;

    final public int e = 1;
    final protected int f = 1;
    final int g = 1;
    final private int h = 1;

    public int a;
    protected int b;
    int c;
    private int d;
}
