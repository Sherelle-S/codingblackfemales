package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.algo.PassiveAlgoLogic;
import codingblackfemales.algo.AddCancelAlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.sotw.marketdata.BidLevel;
import codingblackfemales.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*All you are doing is building your own algo that creates a child order and deletes it, you are not implementing the other algos they are just there to guild you */
public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);


    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

        // PassiveAlgoLogic passiveAlgoLogic = new PassiveAlgoLogic();
        // // passiveAlgoLogic.getActiveChildOrders();
        // // passiveAlgoLogic.evaluate();
        // MyAlgoLogic myAlgoLogic = new MyAlgoLogic();

        // myAlgoLogic.getActiveChildOrders();
        /********
         *
         * Add your logic here....
         *
         */
        
        // if(SimpleAlgoState.getActiveChildOrders()< 0){
        //     PassiveAlgoLogic.evaluate();
        // }else if(SimpleAlgoState.getActiveChildOrders() < 3){
        //     PassiveAlgoLogic.evaluate();
        // }else{
        //     (SimpleAlgoState.)
        // }
        // SimpleAlgoState.getActiveChildOrders();

    // write simple low level code, do not overthing
// PassiveAlgoLogic.evaluate(null);
//  MyAlgoLogic.evaluate(null);
//               getActiveChildOrders();

    final String book = Util.orderBookToString(state);

    logger.info("[MYALGO] Algo Sees Book as:\n" + book);

    final BidLevel nearTouch = state.getBidAt(0);

    long quantity = 5;
    long price = nearTouch.price;
    var totalOrderCount = state.getChildOrders().size();
    final var activeOrders = state.getActiveChildOrders();
    var orderLimitTrigger = 
    final var option = activeOrders.stream().findFirst();

            if(totalOrderCount < 1){
                 logger.info("[MYALGO] Have:" + totalOrderCount + " children, want 1, joining passive side of book with: " + quantity + " @ " + price);
        return new CreateChildOrder(Side.BUY, quantity, price);
            } else if (option.isPresent()) {
                var childOrder = option.get();
                logger.info("[ADDCANCELALGO] Cancelling order:" + childOrder);
                return new CancelChildOrder(childOrder);
            }



    //until we have three child orders....
    if(state.getChildOrders().size() < 1){
        //then keep creating a new one
        logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 1, joining passive side of book with: " + quantity + " @ " + price);
        return new CreateChildOrder(Side.BUY, quantity, price);
    }else{
        logger.info("[MYALGO] Have:" + state.getChildOrders().size() + " children, want 1, done.");
    }


    // if orders are over x add cancel logic;

        return NoAction.NoAction;
    }

    public static void main(String[] args) {

        MyAlgoLogic myAlgoLogic = new MyAlgoLogic();
        System.out.println(myAlgoLogic.huh());
        myAlgoLogic.evaluate(state);

        // System.out.println("What is the damn method");
        
            //  evaluate(null);


        
    }
}