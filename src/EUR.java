public class EUR extends Coin {

    private final double value = 4.23;

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double calculate(double amount) {
        double answer = getValue() * amount;
        return answer;
    }
}
