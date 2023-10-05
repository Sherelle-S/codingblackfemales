package codingblackfemales.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import codingblackfemales.container.AlgoContainer;
import codingblackfemales.marketdata.gen.RandomMarketDataGenerator;
import messages.marketdata.Venue;

public class TimedLogic {

  // Venue venue = new Venue();

  //     //   // Arraylist comprised of data harvested from the market every 15 minutes
  //     // List<Long>marketMonitor = new ArrayList<Long>();
    
  //     // public void collectData(){
  //     //    // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  //     //   ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  //     //   long marketUpdate = 0;
  //     // you made this class you want it to update with the lastest data when market updates.
  //     // you then want to push it to an array and check if last 3 updates were increaing, if so. we sell. if they decrease by e and price is lower than hwta we last sold at. buy again 
  //     //   //new instance of schedule executor service 
  //     //   Runnable harvestMarketData = () -> {

  //     //     // AlgoContainer algoContainer = new AlgoContainer(null, null, null, null);

  //     //     // do we think runTrigger is the signal to run the algo and if so can we use this to trigger running the algo every 15 min?
  //     //     // RunTrigger runTrigger = new RunTrigger();

  //     //     RandomMarketDataGenerator randomMarketDataGenerator = new RandomMarketDataGenerator(marketUpdate, null, marketUpdate, marketUpdate, marketUpdate);

  // //           // this is the task we want to execute every 15 minutes
  // //           // push data to the marketMonitor array.
  // //           // i want the result to go into ta variable called market update that can be added to the markeyMonitor arrayList
  // //           marketUpdate = ?;
           
           
  // //           scheduler.scheduleAtFixedRate(new.Task(marketUpdate), 0, 15, TimeUnit.MINUTES);
  // //           // scheduler.schedule(new Task(i), 10 - i,
  // //           //                    TimeUnit.SECONDS); 
  // //           marketMonitor.add(marketUpdate);
  // //     };
      
  // //     Runnable cancelCollection = () -> varName.cancel(false); scheduler.schedule(canceller, 8.5, Hours);
  // //   //   Runnable canceller = () -> beeperHandle.cancel(false);
  // //   //  scheduler.schedule(canceller, 1, HOURS);
  // //  };
       
  // //       };
  //   //  marketMonitor.add(marketUpdate);
  //   public static void main(String[] args) {
  //     // RandomMarketDataGenerator randomMarketDataGenerator = new RandomMarketDataGenerator(, null, marketUpdate, marketUpdate, marketUpdate);
  //     // construct a venue and then parse it to random market generator:

  //      Venue venue = new Venue(1);
  //      venue.value();

  //   RandomMarketDataGenerator randomMarketDataGenerator = new RandomMarketDataGenerator(5032, venue, 10, 13, 5);

  //   randomMarketDataGenerator.
  //   // find / create market data object, do  a loop. if it increases 3 times execute a cell. 
  //   // venue is intergers 1 london, 2paris, 3amstadam
  
  // }
}
