/**
 * Obtain coin type and returns concrete coin instance
 */
public class CoinsFactory {

    public static Coin getCoinInstance(Coins input){

        Coin elected = null;

        switch (input) {
            case ILS:
                elected = new ILS();
                break;
            case USD:
                elected = new USD();
                break;
            case EUR:
                elected = new EUR();
                break;
        }
        return elected;

    }
}
