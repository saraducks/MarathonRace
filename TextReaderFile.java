package demofinal;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class reads the textfile and get the name,speed,restvalue
 *@author saraducks
 */
public class TextReaderFile extends ThreadRunner {
	private File racetextfile=null;
	ArrayList<ThreadRunner> runnerthread=null;
	public final String Filed_sep ="\t";
	
	/**
	 * The constructor will intialize super class and create new file object
	 */
	
	public TextReaderFile(){
	    super(null,0,0);
		racetextfile= new File("racetextfile.txt");
		}
	
	/**
	 *It will read textfile using FileReader and
	 *@return runnerthread - thread objects
	 * and pass it to super class
	 *catch block checks for exception
	 */
	
	public ArrayList<ThreadRunner> getrunnerlist(){
		BufferedReader in = null;
		runnerthread = new ArrayList<>();
		try{
			in= new BufferedReader(new FileReader(racetextfile));
			String line = in.readLine();
			while(line != null){
				String[] columns = line.split(Filed_sep);
				String name = columns[0];
				String speedstring = columns[1];
				String restvaluestring = columns[2];
				int speed =  Integer.parseInt(speedstring);
				int restvalue = Integer.parseInt(restvaluestring);
                ThreadRunner tr = new ThreadRunner(name,speed,restvalue);
                runnerthread.add(tr);
               
                line = in.readLine();
			}
		}
		catch(IOException ioe){
			  ioe.printStackTrace();
			  return null;
		  }
		return runnerthread;
	}
	/**
	 * This method checks if the file exists 
	 * else it will create new textfile 
	 */
	private void checkfile()
	  {
	      try
	      {
	          if (!racetextfile.exists())
	              racetextfile.createNewFile();
	      }
	      catch(IOException ioe)
	      {
	          ioe.printStackTrace();
	      }
	  }
	/**
	 * This will closes stream  
	 * @param stream
	 */
	 private void close(Closeable stream)
	  {
	      try
	      {
	          if (stream != null)
	              stream.close();
	      }
	      catch(IOException ioe)
	      {
	          ioe.printStackTrace();
	      }
	  }
}
