import java.io.*;
import java.util.*;

public class Controler {
	private static ArrayList<Elevators> elevator_container; 
	private static int num_floors;
	private static int num_elevators;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of floors : ");
		num_floors = sc.nextInt();
		System.out.print("\nEnter number of elevators: ");
		num_elevators = sc.nextInt();	
		
		elevator_container = new ArrayList<Elevators>();
		//Make elevators
		for(int e = 0 ; e < Controler.getNumberOfElevators() ; e++)
		{
			Elevators elevator = new Elevators(e);
			elevator_container.add(elevator);
		}

		Movement.doMovement();
		ElevatorCalls.listen();
	Gui c= new Gui(elevator_container);	
	c.initGraphics(elevator_container);
	}
	
	public static int getNumberOfFloors()
	{
		return num_floors;
	}
	
	public static int getNumberOfElevators( )
	{
		return num_elevators;
	}
	
	public static ArrayList<Elevators> getElevatorContainer()
	{
		return elevator_container;
	}

}
