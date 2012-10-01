public class RunnableShowName implements Runnable
{
   	public static void main(String[] args)
   	{
    	Thread thread1 = new Thread(new RunnableShowName());
    	Thread thread2 = new Thread(new RunnableShowName());
    	
    	System.out.println("We are in MAIN method now-the FIRST time...");
      	thread1.start();
      	System.out.println("We are in MAIN method again-the SECOND time...");
      	thread2.start();
      	System.out.println("We are in MAIN method again-the THIRD time...");

   	}

   	public void run()
  	{
      	for (int i=0; i<10; i++)  //Experiment with the value 
      	{				//of the index variable i eg. 100, 1000
            	//Use static method currentThread to get
            	//reference to current thread and then call
            	//method getName on that reference...
            	System.out.println(
              		 Thread.currentThread().getName()
                        	+ " being executed.");

      	}
   	}
}
