import java.util.*;
import java.math.*;
public class ElevatorCalls implements Runnable ,  SchedulingAlgorithm{
	 ArrayList<Elevators> elevator_container;
	
	public static void listen()
	{
		Thread listener = new Thread(new ElevatorCalls());
		listener.start();
	}
	
	
	@Override
	public void run() 
	{
		try{
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.print("\nInternal or external call: ");
			String ie = sc.next();
			int btype = type(ie);
			if(btype == 1)
			{
				System.out.print("\nEnter the floor you want to go to: ");
				int floor = sc.nextInt();
				System.out.print("\nWhich elevator: ");
				int chosen = sc.nextInt();
			//	int chosen = getNearestElevator(floor,Elevators.NO_DIRECTION);
				Controler.getElevatorContainer().get(chosen).addQueue(floor);
				Controler.getElevatorContainer().get(chosen).serveFloor2();
			
				
				
				
			}
			
			else
			{
			System.out.print("\nEnter the floor you want to go to: ");
			int floor = sc.nextInt();
			int chosen = randomElevator();
			Elevators chosen1 = getNearestElevator(floor,Elevators.MOVING_UP);
			//int chosen = 2;
		//	System.out.println("\nElevator " + Controler.getElevatorContainer().get(chosen).getId() + "is selected.");
		//	Controler.getElevatorContainer().get( chosen).addQueue(floor);
			//Controler.getElevatorContainer().get
		//	Controler.getElevatorContainer().get(chosen).serveFloor2();
			
			chosen1.addQueue(floor);
			chosen1.serveFloor2();
			}
			
			
		} }catch(Exception e) {
			System.out.println("exception" +e);
		}

		

	}
	


	@Override
	public int randomElevator() {
		Random r = new Random();
		int elevator = r.nextInt(Controler.getNumberOfElevators());
		
		return elevator;
	}
	
	public Elevators getNearestElevator(int floorNumber, int direction){
		Elevators closestElevator =null;
		int closestFloor=0;
		int max = Controler.getNumberOfFloors();
		//Elevators currentele= null;
		//int currentfloor=0;
		for (int i=0 ; i<Controler.getNumberOfElevators();i++)
		{
		Elevators	currentele =Controler.getElevatorContainer().get(i);
			System.out.println(currentele.getId());
		int	currentfloor = currentele.getCurrentFloor();
			System.out.println("is at" +currentfloor);
			System.out.println("Elevator motion direction"+Elevators.motion_direction);
			System.out.println( Math.abs(currentfloor-floorNumber)+"compares"+Math.min(currentfloor, floorNumber));
		//	while( Math.abs(currentfloor-floorNumber)<Math.abs(currentfloor- floorNumber)) 
			if(direction==Elevators.MOVING_UP) {
				System.out.println("current floor is"+currentfloor+"closest floor is"+closestFloor+"goto"+floorNumber);
				
				if(currentfloor > closestFloor && currentfloor< floorNumber+1) {
					closestElevator = currentele;
					
					closestFloor= currentfloor;
					System.out.println("Swapped");
				} 
			}  direction=Elevators.NO_DIRECTION;
			 if(direction ==Elevators.MOVING_DOWN)
			{
				if(currentfloor < closestFloor && currentfloor> floorNumber) {
					closestElevator = currentele;
					closestFloor= currentfloor;
			}
			
		} if(direction ==Elevators.NO_DIRECTION)
		{
			if(currentfloor != floorNumber && Math.abs(currentfloor- floorNumber) <=max) {
				
			
				closestElevator = currentele;
				max = Math.abs(currentfloor- floorNumber);
		}
	}
		}
	return closestElevator;	
	}
	public int type(String ie)
	{
		if(ie.equals("i"))
		{
			return 1;
		}

		return 0;
		
	}
}
