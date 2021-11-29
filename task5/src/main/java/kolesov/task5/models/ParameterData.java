package kolesov.task5.models;

import java.io.Serializable;

public class ParameterData implements Serializable {
    private final String name;
    private final Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public ParameterData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Parameter getParameter() {
        return new Parameter(name, value);
    }
}
