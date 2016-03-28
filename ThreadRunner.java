package demofinal;

import java.util.ArrayList;
/**
 * This class create Threads and execute them 
 *@author saraducks
 */
public class ThreadRunner extends Thread{
  private final String runnername;
  private final int runnerspeed;
  private final int runnerrestvalue;
  private int distance = 0;
  
  /**
   * Constructor initialize local variables
   * @param name
   * @param speed
   * @param restvalue
   */
  
  ThreadRunner(String name,int speed,int restvalue){
	  runnername = name;
	  runnerspeed = speed;
	  runnerrestvalue = restvalue;
  }
  
  /**
   * The random number will decide which thread to sleep and run.Once the distance reaches or exceeds 1000 the current
   * thread should interrupt other threads
   */
  
  public void run(){
	  while(distance < 1000){
		  int randomnumber = (int)(Math.random()*100);
		  if(randomnumber > runnerrestvalue){
			  distance += runnerspeed;
		  }
		  System.out.println(this.getRunnerName() + " : " + distance);
		  if(distance >=1000){
	    	  MainApp.finished(this);
		  }
		  try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
	      
	  }
  }
  
  /** 
   * @return RunnerName
   */
  
  public String getRunnerName()
  {
      return this.runnername;
  }  
}
