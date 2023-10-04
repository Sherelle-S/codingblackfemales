package codingblackfemales.gettingstarted;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codingblackfemales.action.Action;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.container.AlgoContainer;
import codingblackfemales.marketdata.gen.MarketDataGenerator;
import codingblackfemales.marketdata.gen.RandomMarketDataGenerator;
import codingblackfemales.marketdata.gen.SimpleFileMarketDataGenerator;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.sotw.marketdata.AskLevel;
import codingblackfemales.sotw.marketdata.BidLevel;
import codingblackfemales.util.Util;
import messages.marketdata.Venue;
import messages.order.Side;

public class TimedLogic implements AlgoLogic {

  private static final Logger logger = LoggerFactory.getLogger(TimedLogic.class);
  private SimpleFileMarketDataGenerator marketDataGenerator;
  private List<Long> marketMonitor = new ArrayList<>();
  private long quantity;// SNIPER
  private long price; // SNIPER

   @Override
      public Action evaluate(SimpleAlgoState state) {

//           var orderBookAsString = Util.orderBookToString(state);

//           logger.info("[TIMEDLOGIC] The state of the order book is:\n" + orderBookAsString);
          

//           var totalOrderCount = state.getChildOrders().size();
//           final var activeOrders = state.getActiveChildOrders();
//           final AskLevel farTouch = state.getAskAt(0);//min seller is looking to accept SNIPER
         
//           //take as much as we can from the far touch....
//         long quantity = farTouch.quantity;
//         long price = farTouch.price;


//           // Arraylist comprised of data harvested from the market every 15 minutes
//       List<Long>marketMonitor = new ArrayList<Long>();

//          if(isMarketOpen()){
//             scheduleDataCollection(farTouch);
//          }
        return NoAction.NoAction;

         
         
        }
       
                //  List<Long>marketMonitor = new ArrayList<Long>();

      


     // this method defines the opening and closing times of the stock market. If the market is open the scheduled event is able to run.
        public boolean isMarketOpen(){
            LocalDateTime currentDateTime = LocalDateTime.now();
            DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();
            LocalTime timeNow = LocalTime.now();
    
            if (dayOfWeek != DayOfWeek.SATURDAY || dayOfWeek != DayOfWeek.SUNDAY){
              LocalTime marketOpen = LocalTime.of(8, 0);
              LocalTime marketClosed = LocalTime.of(16, 30);
              
                if(timeNow.isBefore(marketOpen) || timeNow.isAfter(marketClosed)){
                logger.info("[TIMEDLOGIC] Market is closed");
                return false;
                    
                } else{
                    logger.info("[TIMEDLOGIC] Market is open");
                    return true;
                }
            }
            return isMarketOpen();
        }

        public void scheduleDataCollection(AskLevel farTouch){
        final long instrumentId = 1234;
        final Venue venue = Venue.XLON;
        final long priceLevel = 1000;
        final long priceMaxDelta = 100;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable harvestData = () -> {
          //what are task are you looking to do at scheduled intervals?
        // long quantity = farTouch.quantity;// SNIPER
        long price = farTouch.price; // SNIPER
          
        // push price to marketMonitor arraylist 

        marketDataGenerator.generate(10);
        // marketDataGenerator = new SimpleFileMarketDataGenerator("src/main/java/codingblackfemales/gettingstarted/marketdatasimulation.json", new RandomMarketDataGenerator(instrumentId, venue, priceLevel, priceMaxDelta, 15));
        marketMonitor.add(price);
        logger.info("[TIMEDLOGIC] data harvested");
        System.out.println("Current market monitor list is ");
        marketMonitor.forEach(System.out::println);
        System.out.println("Current market monitor size is " + marketMonitor.size());
        };
        // ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(harvestData, 0, 15, TimeUnit.MINUTES);
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate( harvestData, 0, 15, TimeUnit.SECONDS);
      }

        public void checkForTrend(List<Long> marketMonitor) {
        int consecutiveRises = 0;
        int consecutiveFalls = 0;
        int listSize = marketMonitor.size();
  
        for(int i = listSize - 1; i >= 2; i--){
          long currentPrice = marketMonitor.get(i);
          long previousPrice = marketMonitor.get(i - 1);
          long thirdPrice = marketMonitor.get(i - 2);
  
          if(currentPrice < previousPrice && previousPrice < thirdPrice){
            consecutiveRises++;
            consecutiveFalls = 0;
          }else if(currentPrice > previousPrice && previousPrice > thirdPrice){
            consecutiveFalls++;
            consecutiveRises = 0; //we rest the counter
          }else{
            consecutiveFalls = 0;
            consecutiveRises = 0;
          }
  
          if(consecutiveRises >= 3){
            logger.info("[TIMEDLOGIC] Market trend is rising selling " + quantity + "child orders at " + price);
            new CreateChildOrder(Side.SELL, quantity, price);
            // am i matching or am i buying and selling.
          }else if(consecutiveFalls >= 3){
              logger.info("[TIMEDLOGIC] Market trend is falling buying " + quantity + "child orders at " + price);
            new CreateChildOrder(Side.BUY, quantity, price);
          }
        }
    }

          
  
}