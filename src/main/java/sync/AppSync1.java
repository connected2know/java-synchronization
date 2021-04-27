package main.java.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class AppSync1  implements Runnable
{
	//List<Integer> values = new ArrayList<Integer>();
	 private static Boolean consume = new Boolean(false);
	
    
	 static class P extends Thread{
    	
    	private void  producer(){
    		synchronized(AppSync1.consume) {
		    	try {
				    		System.out.println("Produced");
				    		AppSync1.consume.notify();
				    		AppSync1.consume.wait();
		    	 
		    	}catch(Exception e) {
		    		System.out.println("EXCEPTION P : "+e);
		    	}
    		}
	    }

       @Override	
	   public void run() {
			while(true) {
				 producer();
			}
		}
    }
    
	 static  class  C extends Thread{
    	
	    private void  consumer() {
	    	
    		 synchronized(AppSync1.consume) {
    			 try {
			    		System.out.println("Consumed");
			    		AppSync1.consume.notify();
			    		AppSync1.consume.wait();
	    		
			    	}catch(Exception e) {
			    		System.out.println("EXCEPTION C : "+e);
			    	}
    		 }
	    }
	    
	    @Override	
	    public void run() {
			while(true) {
				consumer();
			}
		}
    }
    
     public static void main( String[] args )
     {
    	 //P prd = new App().new P();
        // C csm = new App().new C();
         P prd = new P();
         C csm = new C();
         prd.start();
         csm.start();
         
     }

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
