package demofinal;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
/**
 * This class reads the xml file and store it in ArrayList 
 * This ArrayLsit is then passed to ThreadRunner constructor to create thread object
 * @author saraducks
 */
public class XmlReaderFile extends ThreadRunner {
   /**
    * The constructor initialize the super class
    */
	XmlReaderFile() {
		super(null, 0, 0);
		}
	/**
	 * The ArrayList<ThreadRunner> getrunnerlist() will create ArrayList by parsing
	 * through xml file and get the runner name,speed,restvalue 
	 * The parameters are passed to ThreadRunner class to create thread object in the try block
	 * The catch block checks for FileNotFoundException and XMLStreamException
	 * @return ArrayList<ThreadRunner> xmlraceraeder 
	 */
	public ArrayList<ThreadRunner> getrunnerlist(){
         
		ArrayList<ThreadRunner> xmlracereader = new ArrayList<>();
		try{
			XMLInputFactory inputfactory = XMLInputFactory.newInstance();
	        FileReader filereader= new FileReader("racexml.xml");
	        XMLStreamReader reader = inputfactory.createXMLStreamReader(filereader);
	        int speed=0,restvalue=0;
	        boolean f1 = false;
	        boolean f2 = false;
	        boolean f3 = false;
	        String name=null;
	        while(reader.hasNext()){
	        	int eventtype = reader.getEventType();
	        	switch(eventtype){
		        	case XMLStreamConstants.START_ELEMENT:
		        		String elementname = reader.getLocalName();
		        		if(elementname.equals("Runner")){
		        			name = reader.getAttributeValue(0);
		        			f1 = true;		        		
		        		}
		        		if(elementname.equals("RunnersMoveIncrement")){
		        			String runnerspeed = reader.getElementText();
		        		    speed = Integer.parseInt(runnerspeed);
		        		    f2 = true;		        			
		        		}
		        		if(elementname.equals("RestPercentage")){
		        			String runnerrestvalue = reader.getElementText();
		        		    restvalue = Integer.parseInt(runnerrestvalue);
		        		    f3 = true;	
		        		}
		           case XMLStreamConstants.END_ELEMENT:
		        		String endelementname = reader.getLocalName();
		        		
		        		if(endelementname.equals("Runner") && f1 && f2 && f3){
		        			ThreadRunner tr = new ThreadRunner(name,speed,restvalue);
		    	        	xmlracereader.add(tr);
		    	        	
		    	        	f1 = false;
		    	        	f2 = false;
		    	        	f3 = false;
		    	        	name = null;
		    	        	speed = 0;
		    	        	restvalue = 0;
		    	        	
		        		}
		        	    
		        	 default:
		        		 break;
	        	}
	        	reader.next();
	        }
	        
		}catch (FileNotFoundException | XMLStreamException e)
        {
            e.printStackTrace();
            return null;
        }
		return xmlracereader;
}
}
