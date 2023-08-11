package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.util.Util;
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

        return NoAction.NoAction;
    }

    public static void main(String[] args) {
        // CreateChildOrder createChildOrder = new CreateChildOrder(null, 10, 25);
       
        CreateChildOrder createChildOrder = new CreateChildOrder(Side.Buy, 100, 5000);
// what is side?
// where do we put the main class.
// how do we access side?
// 

// System.out.println(side.Buy);
// System.out.println(Buy);
// System.out.println(side.Sell);
// SimpleAlgoState simpleAlgoState = new SimpleAlgoState() {
    
// };
        System.out.println(createChildOrder);


        System.out.println("");
    }

}