package codingblackfemales.gettingstarted;

import codingblackfemales.algo.AlgoLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * This test is designed to check your algo behavior in isolation of the order book.
 *
 * You can tick in market data messages by creating new versions of createTick() (ex. createTick2, createTickMore etc..)
 *
 * You should then add behaviour to your algo to respond to that market data by creating or cancelling child orders.
 *
 * When you are comfortable you algo does what you expect, then you can move on to creating the MyAlgoBackTest.
 *
 */
public class MyAlgoTest extends AbstractAlgoTest {
// writing tests start
    @Override
    public AlgoLogic createAlgoLogic() {
        //this adds your algo logic to the container classes
        return new MyAlgoLogic();
    }


    @Test
    public void testDispatchThroughSequencer() throws Exception {

        //create a sample market data tick....
        send(createTick());

        //simple assert to check we had 3 orders created
        assertEquals(container.getState().getChildOrders().size(), 4);
        // assertNotEquals(0, 0, 0);    
        // assertTrue(null, false);
        // see if you can see 'assert test with coverage' it will tell you how many lines that test is actually covering.'
    }

//     @Test 
//     public void testOneChildOrder() throws Exception{
//         send(createTick());
//         // assertEquals(container.getState().getChildOrders().contains(checkIfOrdersCancelled()));
//         // assertEquals(container.getState().getChildOrders().size(), 4);
//     }

    

//     // @Test
//     // public void /*name the method what the test does */(){
//     //     // MyAlgoLogic myAlgoLogic = new MyAlgoLogic();
//     //     // new instance of algoLogic
//     //     // can also do

//     //     send(createTick());
//     //     var myAlgoLogic = new MyAlgoLogic();
//     //     myAlgoLogic.evaluate(null);
//     // }

//     @Test
//     public void tooManyOrdersTest() throws Exception{
//         try {
//             send(createTick());
        
//         var myAlgoLogic = new MyAlgoTest();
//                 assertEquals(container.getState().getActiveChildOrders().size(), 0);
// // create test to see what happens if more than  child orders are created 
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
        
//     }

        
//         @Test
//         public void checkIfOrdersCancelled(){
//           try {
//             send(createTick());
        
//         var myAlgoLogic = new MyAlgoTest();
//                 assertEquals(container.getState().getActiveChildOrders().size(), 0);
// // create test to see what happens if more than  child orders are created 
//         } catch (Exception e) {
//             // TODO: handle exception
        
//             // find what holds cancelled child orders again 
//         }
//     }

//         @Test
//         public void priceCheckTest(){
//             // create test to see what happens if price is too low.
//         }
        

    // created around 4 more tests
    // mvn test > Output.txt
}
