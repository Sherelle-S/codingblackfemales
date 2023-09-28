package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.sotw.marketdata.AskLevel;
import codingblackfemales.sotw.marketdata.BidLevel;
import codingblackfemales.util.Util;
import messages.order.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);

    @Override
    public Action evaluate(SimpleAlgoState state) {

        

        logger.info("[MYALGO] In Algo Logic.... HeLlO");

        final String book = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + book);

        /********
         *
         * Add your logic here....
         *
         */
        // final BidLevel nearTouch = state.getBidAt(0);
        // long price1 = nearTouch.price;
        // long quantity1 = nearTouch.quantity;
         final AskLevel farTouch = state.getAskAt(0);
// far touch was sniper logic
         long quantity = 46;
         long price = farTouch.price;


         var totalOrderCount = state.getChildOrders().size();
         final var activeOrders = state.getActiveChildOrders();
         final BidLevel getBidAt = state.getBidAt(0);
         long bidLevels = state.getBidLevels();// seems to be 3
         long askLevels = state.getAskLevels(); //
         AskLevel askAt = state.getAskAt(0);


         if(totalOrderCount < 45){
            System.out.println("bid level is: "+ getBidAt+" long bif level is: "+bidLevels); //
            System.out.println("ask level is: "+ askAt+" bid at "+ askLevels);
            // if(totalOrderCount > 1 && totalOrderCount < 45){
            //     System.out.println("active child orders "+ activeOrders.get(0)); //returns a class file
            //     System.out.println("total orders "+state.toString()); //returns a clas file
            // }
            
            // System.out.println("near touch "+nearTouch+" price "+price1+" quantity "+quantity1);
            System.out.println(farTouch+"far touch "+price+"price"+quantity);
            logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 45, joining passive side of book with: " + quantity + " @ " + price);
            System.out.println(new CreateChildOrder(Side.BUY, quantity, price));
            System.out.println("active orders are " +state.getActiveChildOrders().toString());
            
            return new CreateChildOrder(Side.BUY, quantity, price);
        }else if(activeOrders.size() > 10){
            final var option = activeOrders.stream().findFirst();
            System.out.println("total order count is" +totalOrderCount);

            if(option.isPresent()){
                    logger.info("[MYALGO] option is present total order count is" + totalOrderCount);
                logger.info("[MYALGO] option is present, active orders are "+ state.getActiveChildOrders());
                // System.out.println("total order count is" +totalOrderCount);
                // System.out.println("active orders are " + state.getActiveChildOrders());
                // var childOrder = option.get();
                // get the child order
                
                // logs info for child order
                
            System.out.println("active orders are " +state.getActiveChildOrders().toString());
            // variable comes from cancel child order file, which finds the first object int the stream.
                logger.info("[MYALGO] option is present active orders are:" + activeOrders);
                
                System.out.println("option is present.");
                var childOrder = option.get();
                logger.info("[MYALGO] Cancelling order:" + childOrder);
                System.out.println("child order is "+childOrder);
                System.out.println("Child order is cancelled.");
                return new CancelChildOrder(childOrder);
            }else{
                return NoAction.NoAction;
            }
        }
        return NoAction.NoAction;
    }
}
