public class USD extends Coin{

    private final double value = 3.52;

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
