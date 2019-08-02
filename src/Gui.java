import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

//import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JPanel{
	public static ArrayList<Elevators> elevator_container;
    
    public Gui(ArrayList<Elevators> e) {
        elevator_container = e;
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        int width = getSize().width;
        int height = getSize().height;
        int padding = width/1000;
        int padding_vertical = height/1600;
        int nfloors = Controler.getNumberOfFloors();
        int rowHeight = height/nfloors;
        int colWidth = width/(elevator_container.size()+1);
        g.setColor(Color.white);
        int diameter = Math.min(colWidth, rowHeight) - 2*padding;
        
        //levels and buttons
    	for (int k=1; k<nfloors; ++k) {
        	for (int i=0; i<elevator_container.size(); ++i) {
	            int level = rowHeight * (nfloors-k-1);
	            g.setColor(Color.black);
	            g.drawLine(0, level, width, level);
	            g.drawLine(0, level+2, width, level+2);
	            //up-buttons
	           // g.setColor(RequestListener.getExternalLight(k, 0) == true?Color.yellow:Color.lightGray);
	            if(k!=nfloors-1)
	            	g.fillOval(elevator_container.size()*colWidth+padding*padding, level+padding_vertical, diameter/3, diameter/3);
	            g.setColor(Color.black);
	            g.drawOval(elevator_container.size()*colWidth+padding*padding, level+padding_vertical, diameter/3, diameter/3);
	            g.drawString("^", elevator_container.size()*colWidth+(padding+2)*padding, level+rowHeight/3-padding_vertical);
	            
	            //down-buttons
	        //    g.setColor(RequestListener.getExternalLight(k, 1) == true?Color.yellow:Color.lightGray);
	            if(k!=0)
	            	g.fillOval(elevator_container.size()*colWidth+padding*padding, level+padding_vertical+diameter/3, diameter/3, diameter/3);
	            g.setColor(Color.black);
	            g.drawOval(elevator_container.size()*colWidth+padding*padding, level+padding_vertical+diameter/3, diameter/3, diameter/3);
	            g.drawString("v", elevator_container.size()*colWidth+(padding+2)*padding, level+rowHeight/3+diameter/3-padding);
        	}
    	}
    	
    	//elevators
        for (double j=0; j<=nfloors-1; j++) {
        	for (int i=0; i<elevator_container.size(); ++i) {
	            int elev_level = (int)(rowHeight * (nfloors-j-1));
	            
	            //elevators' locations
	            if (elevator_container.get(i).getCurrentFloor() == j) {
	            //	int doorSize = elevator_container.get(i).open();
	            	g.setColor(Color.lightGray);
	                g.fillRect(i*colWidth+3*padding, elev_level+padding, diameter-2*padding, diameter);
	                g.setColor(Color.black);
	                g.drawRect(i*colWidth+3*padding, elev_level+padding, diameter-2*padding, diameter);
	                g.drawRect(i*colWidth+3*padding, elev_level+padding, diameter/2-padding, diameter);
	              // if(doorSize>0) {
	                //	g.setColor(Color.black);
	              //	g.fillRect(i*colWidth+3*padding+8*(padding-doorSize), elev_level+padding, (int)((doorSize/4.0)*(diameter-2*padding)), diameter);
	                }
	                g.setColor(Color.red);
	                //floor labels
	                g.drawString(""+(int)j, i*colWidth+(padding+2)*padding, elev_level+rowHeight/3-(padding-2));
	            }
        	}
        }
        
  //  }
    
    public static void initGraphics(ArrayList<Elevators> elevators) {
        
    	
        JFrame frame = new JFrame("Elevator System");
        frame.setSize(1000, 2000);

        Gui gui = new Gui(elevators);
        
        frame.setLayout(new BorderLayout());
        
        frame.add(gui, "Center");
        
        JPanel panel_ext_buttons = new JPanel();
        
        panel_ext_buttons.setLayout(new GridLayout(0,1,0,8));
        extButtons(panel_ext_buttons);
        
        frame.add(panel_ext_buttons, "East");
        
        frame.setLocation(500, 50);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        JPanel panel_int_buttons = new JPanel();
        JFrame btn_frame = new JFrame("Interior Buttons");
        btn_frame.setSize(500, 400);
        
        panel_int_buttons.setLayout(new GridLayout(elevators.size(),1,2,25));
        intButtons(panel_int_buttons);
        
        btn_frame.add(panel_int_buttons);
        
        btn_frame.setLocation(1000, 50);
        btn_frame.setVisible(true);
        btn_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn_frame.setResizable(false);
        
        JPanel panel_int_lights = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // code here
                int width = getSize().width;
                int height = getSize().height;
                int padding = width/100;
                int nelevs = Controler.getNumberOfElevators();
                int nfloors = Controler.getNumberOfFloors();
                int rowHeight = height/nelevs;
                int colWidth = width/(nfloors+1);
                g.setColor(Color.white);
                int diameter = Math.min(colWidth, rowHeight) - 2*padding;
                for (int j=0; j<nelevs; ++j) {
                	for (int i=0; i<nfloors; ++i) {
        	            int level = rowHeight * (nelevs-j-1);
        	            g.setColor(Color.black);
        	            g.drawLine(0, level, width, level);
        	            
        	            //up-buttons
        	           // g.setColor(RequestListener.getInternalLight(nelevs-j-1, i) == true?Color.yellow:Color.lightGray);
        	            g.fillOval(i*colWidth+3*padding, level+padding, diameter, diameter);
        	            g.setColor(Color.black);
        	            g.drawOval(i*colWidth+3*padding, level+padding, diameter, diameter);
        	            g.drawString(""+i, i*colWidth+(padding+2)*padding, level+rowHeight/3-(padding-2));
        	            
                	}
                }
            }
        };
        
        JFrame btn_lights = new JFrame("Interior Lights");
        btn_lights.setSize(500, 400);
        
        
        btn_lights.add(panel_int_lights);
        
        btn_lights.setLocation(1000, 450);
        btn_lights.setVisible(true);
        btn_lights.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn_lights.setResizable(false);
        
        //keep animating
        while(true)
        	animate(gui,panel_int_lights);
        
    }
    
    public static void extButtons(JPanel panel) {
        
        int num_floors = Controler.getNumberOfFloors();

    	JButton[] ext_up = new JButton[num_floors];
    	JButton[] ext_down = new JButton[num_floors];
    	
    	for (int i = 0; i < num_floors; i++){
    		
    		ext_up[i] = new JButton("UP");
			ext_up[i].setBackground(Color.gray);
			ext_up[i].setName((num_floors-1-i)+"ue");
			//ext_up[i].addActionListener(new RequestListener(elevators));
			
			panel.add(ext_up[i]);
    		if(i==0) {
    			ext_up[i].setVisible(true);
    			ext_up[i].setEnabled(true);
    		}
    		
    		ext_down[i] = new JButton("DOWN");
			ext_down[i].setBackground(Color.gray);
			ext_down[i].setName((num_floors-1-i)+"de");
		//	ext_down[i].addActionListener(new RequestListener(elevators));

			panel.add(ext_down[i]);

    		if(i==num_floors-1) {
    			ext_down[i].setVisible(false);
    			ext_down[i].setEnabled(false);
    		}
			
    	}

    }
    
    public static void intButtons(JPanel panel) {
        
        int num_floors = Controler.getNumberOfFloors();
        int num_elevators = elevator_container.size();

    	JButton[][] interior = new JButton[num_elevators][num_floors];
    	JLabel[] label = new JLabel[num_elevators];
    	
    	for (int i = 0; i < num_elevators; i++){
    		label[i] = new JLabel("Elev:" + i);
    		panel.add(label[i]);
    		for (int j = 0; j < num_floors; j++){
    		
	    		interior[i][j] = new JButton(""+j);
	    	//	if(RequestListener.getInternalLight(i, j) == true) {
	    			interior[i][j].setBackground(Color.black);
	    		}
	    	//	else {
	    		//	interior[i][j].setBackground(Color.gray);
	    		}
				
		//		interior[i][j].setName(i+""+j+"i");
				//interior[i][j].addActionListener(new RequestListener(elevators));
				
	//			panel.add(interior[i][j]);
	    		/*if(i==0) {
	    			interior[i][j].setEnabled(false);
	    		}*/
    		}
    		
    	

    

    
    static void animate(JPanel p, JPanel q) {
        try {
            Thread.sleep(250);
        } catch (Exception ex) {};
        p.repaint();
        q.repaint();
    }

    
    
}
