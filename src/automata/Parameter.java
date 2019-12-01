package automata;

public class Parameter {

    private String unit;
    private double value;

    public Parameter(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
