public class Currency {

    private final String name;
    private final double value;
    private final double previousValue;

    public Currency(String name, double value, double previousValue) {
        this.name = name;
        this.value = value;
        this.previousValue = previousValue;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public double getPreviousValue() {
        return previousValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
