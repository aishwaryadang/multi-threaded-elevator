import java.util.LinkedList;
import java.util.PriorityQueue;

public class Elevators {

	private int id;
    private int current_floor = 0;	
    public static  int motion_direction;
    public static final int MOVING_UP = 1;
    public static final int NO_DIRECTION = 0;
    public static final int MOVING_DOWN = -1;
    private PriorityQueue<Integer> callQueue ;
    
	public Elevators(int id) {
		callQueue = new PriorityQueue<Integer>();
		this.id = id;
		motion_direction =NO_DIRECTION;
		this.current_floor=1;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	
	public void setCurrentFloor(int floor)
	{
		this.current_floor = floor;
	}
	
	public int getCurrentFloor()
	{
		return this.current_floor;
	}
	
	public PriorityQueue<Integer> getQueue()
	{
		return this.callQueue;
	}
	
	public void addQueue(int floor)
	{
		this.callQueue.add(floor);
		
	}
	
	synchronized void servefloor() throws InterruptedException {
    	try {
			wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
    	while( getQueue().size() > 0)
    	{ System.out.println("current floor" +getCurrentFloor()+"gotofloor"+getQueue().peek());
    		if( getCurrentFloor() < getQueue().peek() )
    		{
    			for(int i = getCurrentFloor() ; i <= getQueue().peek() ; i++)
    			{
    				setCurrentFloor(i);
    				System.out.print("\nElevator " + getId() + " is currently at floor : "  + getCurrentFloor());
    				motion_direction =MOVING_UP;
    				Thread.sleep(3000);
    				
    			} motion_direction=NO_DIRECTION;
    		}
    		else
    		{
    			for(int i = getCurrentFloor() ; i >= getQueue().peek() ; i--)
    			{
    				setCurrentFloor(i);
    				System.out.print("\nElevator " + getId() + " is currently at floor : "  + getCurrentFloor());
    				motion_direction=MOVING_DOWN;
    				Thread.sleep(5000);
    			}   motion_direction=NO_DIRECTION; 			
    		}
    		getQueue().remove(); motion_direction=NO_DIRECTION;
    		System.out.print("\nElevator " + getId() + " Door Open By: ");
			open();
    		System.out.print("\nElevator " +  getId() + "Door Close By: ");
			close();
			System.out.println();
    	}
		// TODO Auto-generated method stub
		
	}
	
	synchronized void serveFloor2() {
		notify();
	}
	
	int open()
	{  int doorsize;
		for( doorsize = 0; doorsize < 3 ; doorsize ++)
		{
			//System.out.print("\t" + (index + 1) + " meters");
		doorsize=doorsize +1;
				} 
		return doorsize;
		}
	
	void close()
	{
		for(int index = 0; index < 3 ; index ++)
		{
			System.out.print("\t" + (index + 1) + " meters");
			
		}		
	}
}


