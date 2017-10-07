import java.awt.EventQueue;

public class MainClass 
{	
	public static void main(String []args)
	{		
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{	
				Frame frame = new Frame();
			}
		});	
	}	
}
