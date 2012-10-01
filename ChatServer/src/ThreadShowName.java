
public class ThreadShowName extends Thread
{
	public static void main(String[] args)
	{
		ThreadShowName thread1, thread2;

		thread1 = new ThreadShowName();
		thread2 = new ThreadShowName();

		System.out.println("We are in MAIN method now-the FIRST time...");
		thread1.start();
		System.out.println("We are in MAIN method again-the SECOND time...");
		thread2.start();
		System.out.println("We are in MAIN method again-the THIRD time...");
	}

	public void run()
	{
		for (int i=0; i<10; i++)   //Experiment with the value
		{  				//of the index variable i eg. 100, 1000
		System.out.println(getName() + " being executed.");
		}
	}
}
