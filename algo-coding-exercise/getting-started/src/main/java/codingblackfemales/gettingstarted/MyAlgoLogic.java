package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.OrderState;
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

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n " + orderBookAsString);

        /********
         *
         * Add your logic here....
         *
         */
        // final AskLevel farTouch = state.getAskAt(0);
        // long quantity = farTouch.quantity;
        // long price = farTouch.price;

        
        
        // //to check if its working
        // if(state.getChildOrders().size() < 3){
        //     System.out.println("there are orders");
        // }else{
        //     System.out.println("Noting there");
        // }
         // Get the current best bid price from the order book

         final BidLevel nearTouch = state.getBidAt(0);
        //  long currentPrice = state.getBestBidPrice();

         // Define your target price and quantity chose 10 to create a small order
         long quantity = 10;
        //  chose nearTouch price 
         long price = nearTouch.price;
        //  assigned the state.gteChildOrders().size() method to a shorter variable for ease of reusability. 
         var totalOrderCount = state.getChildOrders().size();
        //  did the same for get child orders. variable names have come from within the codebase for consistency.
         final var activeOrders = state.getActiveChildOrders();
         

        if(totalOrderCount < 4){
            // if total order count is less than 4 create a child order
            logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 4, joining passive side of book with: " + quantity + " @ " + price);
            // logger logs order and logs what you want to happen
            return new CreateChildOrder(Side.BUY, quantity, price);
           
        }else if(activeOrders.size() > 0){
            // if active orders is greater than 0, it meets the terms to cancel them;
            final var option = activeOrders.stream().findFirst();
            // variable comes from cancel child order file, which finds the first object int the stream.

            if (option.isPresent()){
                // if the above variable is present
                var childOrder = option.get();
                // get the child order
                logger.info("[MYALGO] Cancelling order:" + childOrder);
                // logs info for child order
                return new CancelChildOrder(childOrder);
                // and cancel it.
            }else{
                return NoAction.NoAction;
                // or else preform no action
            }
        }
        logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 4, done.");
        // logs the order and what was returned
        return NoAction.NoAction;
        // for the outer loop on the first conditional we requir no action be taken if the first condition is not met.
    }
    // public static void main(String[] args) {
    //     System.out.println("Hello world");

    //     MyAlgoLogic myAlgoLogic = new MyAlgoLogic();
 
    //     myAlgoLogic.evaluate(OrderState.CANCELLED);
    // }
}
