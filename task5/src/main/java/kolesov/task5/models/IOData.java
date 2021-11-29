package kolesov.task5.models;

import java.io.Serializable;
import java.util.List;

public class IOData implements Serializable {
    private final List<String> expressions;
    private final List<ParameterData> parameters;

    public List<String> getExpressions() {
        return expressions;
    }

    public List<ParameterData> getParameters() {
        return parameters;
    }

    public IOData(List<String> expressions, List<ParameterData> parameters) {
        this.expressions = expressions;
        this.parameters = parameters;
    }
}
