package artefact.classes.fields;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class EnumFields {

    private final TestEnum1 nullEnum = null;
    private final SingleEnum singleEnum1 = SingleEnum.ENUM1;
    private final SingleEnum singleEnum2 = null;
    private TestEnum1 testEnum1 = TestEnum1.ENUM1;
    private Object object;

    public EnumFields(final TestEnum1 testEnum1) {
        this.testEnum1 = testEnum1;
    }
}


