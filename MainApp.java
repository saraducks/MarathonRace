package demofinal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This main class gets input from user 
 * @author saraducks
 */
public class MainApp {
	//Initializing ArrayList<ThreadRunner> from ThreadRunner class
    public  static ArrayList<ThreadRunner> runner = null;
	public static void main(String[] args) {
		// Welcome message 
        System.out.println("Welcome to the race app");
        System.out.println("Enter your choice");
        Scanner sc = new Scanner(System.in);
        int casenumber = Validator.getIntegerRange(sc, 1, 5);
        TextReaderFile trf ;
        XmlReaderFile xmf;
        DerbyRaceReader drr;
        
        //This get input from user and starts thread execution
        switch(casenumber){
        case 1:
            trf = new TextReaderFile();
            System.out.println(trf);
        	runner = trf.getrunnerlist();
        	startrace(runner);
        	//System.out.println(runner);
        	break;
        case 2:
        	xmf = new XmlReaderFile();
        	System.out.println(xmf);
        	runner = xmf.getrunnerlist();
        	startrace(runner);
        	//System.out.println(runner);
        	break;
        case 3:
        	runner = new ArrayList<>();
        	runner.add(new ThreadRunner("buffalo",30,90));
        	runner.add(new ThreadRunner("girrafe",70,30));
        	startrace(runner);
        	break;
        case 4:
        	drr = new DerbyRaceReader();
        	System.out.println(drr);
        	runner = drr.getrunnerlist();
        	startrace(runner);
        	break;
        }
	}
	
	/**
	 * This will start the thread execution
	 * @param runner - ArrayList ThreadRunner
	 */
	public static void startrace(ArrayList<ThreadRunner> runner){
		  for(ThreadRunner r:runner){
			  ThreadRunner race = r;
			  race.setName(race.getName());
			  race.start();
		  }
		  try{
			  for(ThreadRunner r:runner){
				  ThreadRunner race = r;
				  race.join();
			  }
		  } catch (InterruptedException e) {
		             e.printStackTrace();
		  }
	}
	
	/**
	 * The race is finished and winner name is displayed
	 * @param winner
	 */
	public static synchronized void finished(ThreadRunner winner){
		String winnerName = winner.getRunnerName();
    		System.out.println(winnerName + " : I finished!");
    		System.out.println(" ");
    		System.out.println("The race is over!The " +  winnerName + " is the winner.\n");
    		for (ThreadRunner tr : runner) {
    			if (tr.getRunnerName().equals(winnerName) != true) {
    				System.out.println(tr.getRunnerName() + " : You beat me fair and square!");
    				tr.interrupt();
    			}
    		}
    }	
}

