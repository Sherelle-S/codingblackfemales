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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);
    private Map<Long, Long> marketMonitor = new HashMap<>();

    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

        /********
         *
         * Add your logic here....
         *
         */

        TimedLogic timedLogic = new TimedLogic(marketMonitor);
        final String book = Util.orderBookToString(state);
        final AskLevel farTouch = state.getAskAt(0);
        logger.info("[MYALGO] Algo Sees Book as: \n" + book);
        var totalOrderCount = state.getChildOrders().size();
        var activeOrders = state.getActiveChildOrders();
        
        //take as much as we can from the far touch....
        long quantity = farTouch.quantity;
        long price = farTouch.price;

        logger.info("[MYALGO] Initial quantity" + quantity + " price " + price);

        if(timedLogic.isMarketOpen()){
            if(totalOrderCount < 1175){
                timedLogic.scheduleDataCollection(farTouch);
                // BidLevel level = state.getBidAt(0);
                // final long price = level.price;
                // final long quantity = level.quantity;
                // logger.info("[MYALGO] Adding order for" + quantity + "@" + price);

                // return new CreateChildOrder(Side.BUY, quantity, price);
                logger.info("[MYALGO] current child orders are " + totalOrderCount);
                logger.info("[MYALGO] total active orders" + activeOrders.size());
                
            }

            // timedLogic.triggerTrendCheck();
            

            if(timedLogic.getMarketMonitor().size() > 2){
                    timedLogic.checkForTrend();
                }
                if(timedLogic.getMarketMonitor().size() > 2000){
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
        }else{
            return NoAction.NoAction;
                }
            
        } else{
            return NoAction.NoAction;
        }
    }

    // Original MyAlgoLogic 

//         final String book = Util.orderBookToString(state);
//         // final AskLevel farTouch = state.getAskAt(0);
//         logger.info("[MYALGO] Algo Sees Book as: \n" + book);
//         var totalOrderCount = state.getChildOrders().size();
//         var activeOrders = state.getActiveChildOrders();
//         TimedLogic timedLogic = new TimedLogic(marketMonitor);
        
//         //take as much as we can from the far touch....
//         // long quantity = farTouch.quantity;
//         // long price = farTouch.price;

//         // logger.info("[MYALGO] Initial quantity" + quantity + " price " + price);

//         if(timedLogic.isMarketOpen()){
//             if(totalOrderCount < 75){
//                 final BidLevel nearTouch = state.getBidAt(0);                
//                 // final long price = level.price;
//                 // final long quantity = level.quantity;
//                 long quantity = nearTouch.quantity; //fix this quantity thing
//         long price = nearTouch.price;
//                 logger.info("[MYALGO] Adding order for" + quantity + "@" + price);
//                 logger.info("[MYALGO] Initial quantity" + quantity + " price " + price);

//                 return new CreateChildOrder(Side.SELL, quantity, price);
//             }

//              if(activeOrders.size() > 7){
//             final var option = activeOrders.stream().findFirst();
//             // option is an object
//              logger.info("[MYALGO] Have:" + activeOrders.size() + " children, want 7, done.");
//             if (option.isPresent()){
//                 logger.info("[MYALGO] active orders are "+ activeOrders.size());
//                 // active orders is what we will track to cancel the orders
//                 var childOrder = option.get();
//                 // get the child order
//                 logger.info("[MYALGO] Cancelling order:" + childOrder);
//                 // logs info for child order
//                 logger.info("[MYALGO] option is present, number of active orders are "+ activeOrders.size());
//                 return new CancelChildOrder(childOrder);
//             }else{
//                 // this else is causing failure
//                 return NoAction.NoAction;
//             }
 
//         }else{
//             return NoAction.NoAction;
//         }
            
//         } else{
//             return NoAction.NoAction;
//         }
        
//     }
// }
}