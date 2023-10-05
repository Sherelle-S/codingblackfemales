package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AddCancelAlgoLogic;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.marketdata.gen.MarketDataGenerator;
import codingblackfemales.sotw.OrderState;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.sotw.marketdata.AskLevel;
import codingblackfemales.sotw.marketdata.BidLevel;
import codingblackfemales.util.Util;
import messages.order.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);
              
    @Override
    public Action evaluate(SimpleAlgoState state) {

        logger.info("[MYALGO] In Algo Logic.... HeLlO");

        // below now called book
        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n " + orderBookAsString);

        /********
         *
         * Add your logic here....
         *
         */
        // below is sniper logic
        final AskLevel farTouch = state.getAskAt(0);
        long quantity = 10;
        long price = farTouch.price;

        
        
        //* code not in final to check if its working
        // if(state.getChildOrders().size() < 4){
        //     System.out.println("there are orders");
        // }else{
        //     System.out.println("Noting there");
        // }
        //  // Get the current best bid price from the order book

        //  final BidLevel nearTouch = state.getBidAt(0);
        //  long currentPrice = state.getBestBidPrice();

        //  // Define your target price and quantity chose 10 to create a small order
        //  long quantity = 10;
        // //  chose nearTouch price 
        //  long price = nearTouch.price;
        //  assigned the state.gteChildOrders().size() method to a shorter variable for ease of reusability. 
         var totalOrderCount = state.getChildOrders().size();
        //  did the same for get child orders. variable names have come from within the codebase for consistency.
         final var activeOrders = state.getActiveChildOrders();
         

        if(totalOrderCount < 4){
            // System.out.println(nearTouch +"near touch");
            // System.out.println("near touch price is "+nearTouch.price);
            // System.out.println("near touch quantity is "+nearTouch.quantity);
            // System.out.println("total order count is " +totalOrderCount);
            // System.out.println("active orders are " +activeOrders);
            // if total order count is less than 4 create a child order
            logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 4, joining passive side of book with: " + quantity + " @ " + price);
            // logger logs order and logs what you want to happen
            // System.out.println("state is evaluated at "+evaluate(state)); 
            // causes stack overflow
            // logger.info("[MYALGO] is this state evaluated "+ evaluate(state));
            return new CreateChildOrder(Side.BUY, quantity, price);
           
        }else if(activeOrders.size() > 4){
            // if active orders is greater than 0, it meets the terms to cancel them;
            final var option = activeOrders.stream().findFirst();
            /* get rid*/logger.info("[MYALGO] total order count is" + totalOrderCount);
            // logger.info("[MYALGO] active orders are --" + state.getActiveChildOrders().toString());
            // logger.info("[MYALGO] active orders are:" + activeOrders.toString());
            // logger.info("[MYALGO] lets look at state" + Arrays.toString(state));

             System.out.println("total order count is" +totalOrderCount);
            // System.out.println("active orders are " +state.getActiveChildOrders().toString());
            // variable comes from cancel child order file, which finds the first object int the stream.

            if (option.isPresent()){
                // if the above variable is present
                logger.info("[MYALGO] option is present total order count is" + totalOrderCount);
                logger.info("[MYALGO] option is present, active orders are "+ state.getActiveChildOrders());
                // System.out.println("total order count is" +totalOrderCount);
                // System.out.println("active orders are " + state.getActiveChildOrders());
                var childOrder = option.get();
                // get the child order
                logger.info("[MYALGO] Cancelling order:" + childOrder);
                // logs info for child order
                logger.info("[MYALGO] option is present active orders are:" + activeOrders);
                
                return new CancelChildOrder(childOrder);

                
                // and cancel it.
                //  System.out.println("total order count is" +totalOrderCount);
            // System.out.println("active orders are " +state.getActiveChildOrders().toString());
            }else{
                return NoAction.NoAction;
                // or else preform no action
            }
        }
        logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 4, done.");
        // logs the order and what was returned
        return NoAction.NoAction;
        // for the outer loop on the first conditional we requir no action be taken if the first condition is not met.


        /*this is the logic for the algo that should actually trade below */

      
    }
    // public static void main(String[] args) {
    //     System.out.println("Hello world");

    //     MyAlgoLogic myAlgoLogic = new MyAlgoLogic();
 
    //     // myAlgoLogic.evaluate(OrderState.CANCELLED);
    //     // myAlgoLogic.C
    //     AlgoLogic algoLogic = new AlgoLogic();
    //     Container container = new 
    //     MarketDataGenerator() {
    //         // try to use this and tie it to Timed logic to get your algo to run
    //         // create a job that triggers it at incraments 
    //         // maybe reduve the lag between the triggers
    //         // webhook to thrid party or open sourced data and stretch like that
    //     }
    // }
}
