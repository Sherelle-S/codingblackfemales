package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.container.AlgoContainer;
import codingblackfemales.container.RunTrigger;
import codingblackfemales.marketdata.api.MarketDataMessage;
import codingblackfemales.marketdata.gen.MarketDataGenerator;
import codingblackfemales.marketdata.gen.SimpleFileMarketDataGenerator;
import codingblackfemales.sotw.ChildOrder;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.sotw.marketdata.AskLevel;
import codingblackfemales.sotw.marketdata.BidLevel;
import codingblackfemales.gettingstarted.TimedLogic;
import codingblackfemales.util.Util;
import messages.order.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);

    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

        /********
         *
         * Add your logic here....
         *
         */

         TimedLogic timedLogic = new TimedLogic();
        final String book = Util.orderBookToString(state);

        logger.info("[MYALGO] Algo Sees Book as: \n" + book);

        var totalOrderCount = state.getChildOrders().size();

        final var activeOrders = state.getActiveChildOrders();

        final AskLevel farTouch = state.getAskAt(0);

        //take as much as we can from the far touch....
        long quantity = farTouch.quantity;
        long price = farTouch.price;


          // Arraylist comprised of data harvested from the market every 15 minutes
      List<Long>marketMonitor = new ArrayList<Long>();

      boolean isMarketOpen = true;

      if(timedLogic.isMarketOpen()){
        logger.info("[TIMEDLOGIC] running scheduled event.");
        timedLogic.scheduleDataCollection(farTouch);
      }else{
                if(activeOrders.size() > 5){
            final var option = activeOrders.stream().findFirst();
            // option is an object
             logger.info("[MYALGO] Have:" + activeOrders.size() + " children, want 5, done.");
            if (option.isPresent()){
                logger.info("[MYALGO] active orders are "+ activeOrders.size());
                // active orders is what we will track to cancel the orders
                var childOrder = option.get();
                // get the child order
                logger.info("[MYALGO] Cancelling order:" + childOrder);
                // logs info for child order
                logger.info("[MYALGO] option is present, number of active orders are "+ activeOrders.size());
                return new CancelChildOrder(childOrder);
            }else{
                // this else is causing failure
                return NoAction.NoAction;
            }


      }

    //   logger.info("[TIMEDLOGIC] latest market monitor is " + Arrays.toString(marketMonitor));
      logger.info("[TIMEDLOGIC] market monitor has " + marketMonitor.size() + " items inside.");
    //   System.out.println(Arrays.toString(marketMonitor));
      marketMonitor.forEach(System.out::println);
      timedLogic.checkForTrend(marketMonitor);
      return NoAction.NoAction;
    


//       SimpleFileMarketDataGenerator simpleGenerator = new SimpleFileMarketDataGenerator(book, null);
//       simpleGenerator.generate(0);

//       AlgoContainer algoContainer = new AlgoContainer(null, null, null, null);
//       algoContainer.runAlgoLogic();

//       RunTrigger runTrigger = new RunTrigger();

//         // MarketDataGenerator marketDataGenerator = new MarketDataGenerator() {

//         //     @Override
//         //     public MarketDataMessage next() {
//         //         // TODO Auto-generated method stub
//         //         throw new UnsupportedOperationException("Unimplemented method 'next'");
//         //     }
            
//         // };


// //         BELOW IS THE INITIAL ALGO THAT I WAS PLAYING WITH 
// final String book = Util.orderBookToString(state);

//         logger.info("[MYALGO] Algo Sees Book as:\n" + book);

//         var totalOrderCount = state.getChildOrders().size();

      

//         final var activeOrders = state.getActiveChildOrders();

//         if(totalOrderCount < 45){
//             //logger and sys out will only show until TOC reaches required num
//             BidLevel level = state.getBidAt(0);
//             AskLevel farTouch = state.getAskAt(0);
//             long quantity = farTouch.quantity;
//             long price = farTouch.price;
//             // final long price = level.price;
//             // final long quantity = level.quantity;
//             logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 45. Adding order for" + quantity + "@" + price);
//             System.out.println("total order count is " +totalOrderCount); 
//             ChildOrder childOrder = new ChildOrder(null, price, quantity, price, 0);
//             logger.info("[MYALGO] child order quantities:" + childOrder.getQuantity());
//             return new CreateChildOrder(Side.BUY, quantity, price);
//         }
//         if(activeOrders.size() > 5){
//             final var option = activeOrders.stream().findFirst();
//             // System.out.println("this is option "+option);
//             // option is an object
//             // System.out.println("total order count is" +totalOrderCount);
//             // total num of orders place not num once they start cancelling. for that check active orders 
//              logger.info("[MYALGO] Have:" + activeOrders.size() + " children, want 5, done.");
//             if (option.isPresent()){
                
//                 // logger.info("[MYALGO] option is present total order count is" + totalOrderCount);
//                 // stays constant at totalOrder count

//                 // logger.info("[MYALGO] option is present, active orders are "+ activeOrders);
// // shows order ids
//                 logger.info("[MYALGO] active orders are "+ activeOrders.size());
//                 // active orders is what we will track to cancel the orders
                
//                 // System.out.println("total order count is" +totalOrderCount);
//                 // System.out.println("active orders are " + state.getActiveChildOrders());
//                 var childOrder = option.get();
//                 // get the child order
//                 logger.info("[MYALGO] Cancelling order:" + childOrder);
//                 // logs info for child order
//                 logger.info("[MYALGO] option is present, number of active orders are "+ activeOrders.size());
//                 // logger.info("[MYALGO] option is present active orders are:" + activeOrders);
//                 // logger.info("[MYALGO] bolo total order count is now that we are cancelling is " +totalOrderCount);
//                 // logger.info("[MYALGO] child order to string"+childOrder.toString());
//                 // to string and deep to string do not work on childOrder, activeOrders or totalOrderCount. may work abother way not yet found
                
//                 return new CancelChildOrder(childOrder);
//             }else{
//                 // this else is causing failure
//                 return NoAction.NoAction;
//             }
//         }else{
            
//         // logs the order and what was returned
//         return NoAction.NoAction;
        }
return NoAction.NoAction;
    }
}
