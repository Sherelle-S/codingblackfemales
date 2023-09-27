package codingblackfemales.gettingstarted;

import codingblackfemales.marketdata.api.MarketDataMessage;
import codingblackfemales.marketdata.gen.MarketDataGenerator;

public class Main {

public static void startApp(){
    MarketDataGenerator marketDataGenerator = new MarketDataGenerator() {

        @Override
        public MarketDataMessage next() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'next'");
        }
        //find out how this works and use it to run your code
    };

}

    public static void main(String[] args) {
        startApp();
    }
}
