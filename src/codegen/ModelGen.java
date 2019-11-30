package codegen;

public class ModelGen {
    private String name;
    private String parameters = "";
    private String variables = "";
    private String initials = "\ninitial equation";
    private String equations = "\nequation";

    public ModelGen(String name) {
        this.name = name;
    }

    public String getParameters() {
        return parameters;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String name, String value) {
        setInitials(name, value);
        this.variables = this.variables + "\n\tReal " + name + ";";
    }

    public void setParameters(String name, String unit, String value) {
        this.parameters = this.parameters + "\n\tparameter Real " + name + "(unit = " + unit + ") = " + value + ";";
    }

    public void setInitials(String name, String value) {
        this.initials = this.initials + "\n\t" + name + " = " + value + ";";
    }

    public void setEquations(String var, String value) {
        this.equations = this.equations + "\n\tder(" + var + ") = " + value + ";";
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String out = "model " + name + "\t" + parameters + variables + initials + equations + "\nend " + name + ";";







        return out;
    }
}
