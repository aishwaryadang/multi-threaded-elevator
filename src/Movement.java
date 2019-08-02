import java.util.ArrayList;

public class Movement implements Runnable{

	Elevators e;
	
	Movement(Elevators e)
	{
		this.e = e;
	}
	
	public static ArrayList<Thread> movement;
	public static void doMovement()
	{
		movement = new ArrayList<Thread>();
		for(int e1 = 0 ; e1 < Controler.getNumberOfElevators() ; e1++)
		{
			Elevators elevator = Controler.getElevatorContainer().get(e1);
	
			Thread m = new Thread(new Movement(elevator));
			movement.add(m);
			System.out.println("Elevator " + elevator.getId() + " is currently at floor : " + elevator.getCurrentFloor());
			m.start();
		} 

	}

	@Override
	public void run() {
		while(true)
		{
			try {
				e.servefloor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			
				
		}
	}


	
	
}


