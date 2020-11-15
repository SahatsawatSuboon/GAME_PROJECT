package javaapplication7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class playstate1 extends JPanel implements ActionListener{
    
	private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("stage1_bg.gif"));
	private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("stage2.gif"));
        private final ImageIcon imgstate3 = new ImageIcon(this.getClass().getResource("stage3.gif"));
        private final ImageIcon imgtemp = new ImageIcon(this.getClass().getResource("temp.png"));
	private final ImageIcon imgrocket = new ImageIcon(this.getClass().getResource("ship3.gif"));	
        private final ImageIcon imgHP3 = new ImageIcon(this.getClass().getResource("hp3.png"));
        private final ImageIcon imgHP2 = new ImageIcon(this.getClass().getResource("hp2.png"));
        private final ImageIcon imgHP1 = new ImageIcon(this.getClass().getResource("hp1.png"));
	private final ImageIcon back = new ImageIcon(this.getClass().getResource("exit_play.png"));
	ship m = new ship();
        
	homegames hg = new homegames();
	ImageIcon feildover = new ImageIcon(this.getClass().getResource("end.gif"));
	ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit_play.png"));
	ImageIcon restart = new ImageIcon(this.getClass().getResource("start.jpg"));
        JButton BStartover = new JButton(restart);
	JButton BExitover  = new JButton(exitover);
	
	private JLabel score = new JLabel(); 
        
	public JButton BExithome  = new JButton(back); 
	
            
	
        
	public ArrayList<Fireball> fireball = new ArrayList<Fireball>();
        public ArrayList<ship> ship = new ArrayList<ship>();
	public ArrayList<ball1> ba1 = new ArrayList<ball1>();
	public ArrayList<ball2> ba2 = new ArrayList<ball2>();  
        public ArrayList<ball4> ba4 = new ArrayList<ball4>();
	public ArrayList<ball5> ba5 = new ArrayList<ball5>();
        
	public int times ;
        
	public int HP = 3;
	public int rs1 = 1;
	public int rs2 = 2;
        public int rs3 = 3;
	boolean timestart = true;
	boolean startball = false;
	
	private gameover gover = new gameover();
	public int scor = 0;
        public int ammo = 100 ;
	boolean paralyze1 = false;
	int time_paralyze=5;
	
        
   
        
	Thread time = new Thread(new Runnable(){
            
            public void run(){
		while(true){
                    try{
			Thread.sleep(10);
                    }catch(Exception e){ }
                    
                    if(timestart == false){
			repaint();
                        
                    }
		}
            }
	});
	
        
        
	Thread actor = new Thread(new Runnable(){
            public void run(){
		while(true){
                	try{
                            Thread.sleep(1);
			}catch(Exception e){}
                            repaint();
		}
            }
	});
	Thread tballs1 = new Thread(new Runnable(){
            public void run() {
                while(true){
                	try{
                            if(startball == false){
				Thread.sleep((long)(Math.random()*10000)+2000);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startball == false){
                            ba1.add(new ball1());
			}
		}
            }
	});
	Thread tballs2 = new Thread(new Runnable(){
            public void run() {
		while(true){
			try{
                            if(startball==false){
				Thread.sleep((long)(Math.random()*10000)+2000);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startball == false){
                            ba2.add(new ball2());
			}
		}
            }
	});
        
	
	Thread tballs5 = new Thread(new Runnable(){
            public void run() {
            	while(true){
			try{
                            if(startball==false){
                                Thread.sleep((long)(Math.random()*10000)+2000);
                            }
			}catch(InterruptedException e){
                            e.printStackTrace();
			}
			if(startball == false){
                            ba5.add(new ball5());
			}
		}
            }
	});
        
       
        
	Thread paralyze = new Thread(new Runnable(){
            public void run(){
		while (true){
			if(time_paralyze < 1){
                            paralyze1 = false;
                            time_paralyze = 5;
			}
			try{
                            Thread.sleep(5000);
			}catch(InterruptedException e){e.printStackTrace();}
		}
            }
	});
        Thread t = new Thread(new Runnable(){
            public void run() {
		while(true){
                	if(timestart == false){
                            times = (times-1) ;
                            if(paralyze1){
				time_paralyze--;
                            }
			}
			try{
                            Thread.sleep(1000);
			}catch(InterruptedException e)
			{
                            e.printStackTrace();
			}
		}
            }
	});
	
	playstate1(){
		this.setFocusable(true);
		this.setLayout(null);
		
		BExithome.setBounds(750,15,155,80);
		
		BExithome.addActionListener(this);
		
		BExithome.addActionListener(this);
		
		this.add(BExithome);
		this.add(score);
		
		
		this.addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){
                        int a = e.getKeyCode();
			if(!paralyze1){
			    if(a==KeyEvent.VK_LEFT){
                                
			     	m.x-=20;
                                
                                m.count++;
                            }
                            else if(a == KeyEvent.VK_RIGHT){
                                
                               m.x+=20;
                               
                               m.count++;
			   }
                           
                            if(m.count>3){
                                m.count=0;
                            }
                            else if(a == KeyEvent.VK_SPACE){
                               m.count=5;
			       fireball.add(new Fireball(m.x,550));
                               ;
			    }
			}
                    }
                    public void keyReleased(KeyEvent e){
			m.count=0;
		    }
		});
		
		m.x = 400;
                m.y = 400;
                
		time.start();
		actor.start();
                
		t.start();
                
		tballs1.start();
		tballs2.start();
		tballs5.start();
		paralyze.start();
	}
	
	
	
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(times <= 0 || HP<=0){
               
                this.remove(BExithome);
                this.setLayout(null);
                g.drawImage(feildover.getImage(),0,0,1000,800,this);
		g.setColor(Color.white);
                g.setFont(new Font("Mitr",Font.CENTER_BASELINE,40));
                
                g.drawString("SCORE   "+scor,380,200);	     
                g.setFont(new Font("Mitr",Font.CENTER_BASELINE,70));
                g.drawString("GAME OVER",290,150);
                g.drawImage(imgrocket.getImage(), 580, 360, 600, 600, this);
				    
            }else if(scor >=100){
                g.drawImage(imgstate2.getImage(),0,0,1000,800,this);
                if(paralyze1){
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Hobo Std",Font.BOLD,50));
                    
                    g.drawString("AHHHH !!!", m.x+100, 560);
                }else{
                    g.drawImage(m.k.getImage(), m.x, 550,200,200, this);
                }
   		if(m.x<-90){
                    m.x=this.getWidth()-10;
		}
		if(m.x>this.getWidth()){
                    m.x=-90;
		}
   		for(int i=0;i<fireball.size();i++){
                    Fireball ba = fireball.get(i);
      		    g.drawImage(ba.imfire.getImage(), ba.x+80, ba.y,50,50, null);
      		    ba.move();
      		    ba.count++;
      		    if(ba.y<0){
      		    	fireball.remove(i);
      		    }
   		}
                
 		//===========ball1================
                
		for(int i=0 ; i<ba1.size();i++){
                    g.drawImage( ba1.get(i).getImage() ,ba1.get(i).getX(),ba1.get(i).getY(),100,100,this);
		}
                for(int i=0 ; i<fireball.size();i++){
                    for(int j=0 ; j<ba1.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba1.get(j).getbound())){
                            ba1.remove(j);
			    fireball.remove(i);
                            scor += 20;
			    g.drawString("+10",m.x+100,650);
                        } 
		    }
		}
                
                
                
		//===========ball2================
                
		for(int i=0 ; i<ba2.size();i++){
                    g.drawImage(ba2.get(i).getImage(),ba2.get(i).getX(),ba2.get(i).getY(),100,100,this);
		}
		for(int i=0 ; i<fireball.size();i++){
		    for(int j=0 ; j<ba2.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba2.get(j).getbound())){
			    ba2.remove(j);
			    fireball.remove(i);
			    
                            times += 10;
			    g.drawString("+20",m.x+100,650);
   			} 
		    }
		}
                
                
                
                
		//===========ball5================
                
		for(int i=0 ; i<ba5.size();i++){
		    g.drawImage(ba5.get(i).getImage(),ba5.get(i).getX(),ba5.get(i).getY(),100,100,this);
		}
		for(int i=0 ; i<fireball.size();i++){
                    for(int j=0 ; j<ba5.size();j++){
                        if(Intersect(fireball.get(i).getbound(),ba5.get(j).getbound())){
                            ba5.remove(j);
			    fireball.remove(i);
			    scor -=20;  
                            times -= 10;
			    HP=HP-1;
			    g.drawString("-1HP",m.x+100,580);
			    g.drawString("-20",m.x+100,650);
			} 
		    }
		}
		      
		g.drawImage(imgtemp.getImage(),0,0,1000,800,this);
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.setColor(Color.white);
		g.drawString("SCORE",445, 55);
                g.setFont(new Font("Mitr",Font.PLAIN,70));
                if (scor == 0){
                    g.drawString(""+scor,470, 115);
                }
                else if (scor>=10){
                    g.drawString(""+scor,450, 115);
                }
                else if (scor<=-1){
                    g.drawString(""+scor,440, 115);
                }
                else{
                    g.drawString(""+scor,470, 115);
                }
                
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.drawString("ROUND : "+rs3,140,45);
		g.drawString("Time :  "+times,140,85);
		
                if (HP == 3){
                    g.drawImage(imgHP3.getImage(),40,20,30,70,this);
                }
                else if (HP == 2){
                    g.drawImage(imgHP2.getImage(),40,20,30,70,this);
                }
                else if (HP == 1){
                    g.drawImage(imgHP1.getImage(), 40, 20, 30, 70, this);
                }
		      
            }else if(scor >=50){
                g.drawImage(imgstate3.getImage(),0,0,1000,800,this);
                if(paralyze1){
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Hobo Std",Font.BOLD,50));
                    
                    g.drawString("AHHHH !!!", m.x+100, 560);
                }else{
                    g.drawImage(m.k.getImage(), m.x, 550,200,200, this);
                }
   		if(m.x<-90){
                    m.x=this.getWidth()-10;
		}
		if(m.x>this.getWidth()){
                    m.x=-90;
		}
   		for(int i=0;i<fireball.size();i++){
                    Fireball ba = fireball.get(i);
      		    g.drawImage(ba.imfire.getImage(), ba.x+80, ba.y,50,50, null);
      		    ba.move();
      		    ba.count++;
      		    if(ba.y<0){
      		    	fireball.remove(i);
      		    }
   		}
                
 		//===========ball1================
                
		for(int i=0 ; i<ba1.size();i++){
                    g.drawImage( ba1.get(i).getImage() ,ba1.get(i).getX(),ba1.get(i).getY(),100,100,this);
		}
                for(int i=0 ; i<fireball.size();i++){
                    for(int j=0 ; j<ba1.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba1.get(j).getbound())){
                            ba1.remove(j);
			    fireball.remove(i);
                            scor += 30;
			    g.drawString("+10",m.x+100,650);
                        } 
		    }
		}
                
                
                
                
		//===========ball2================
                
		for(int i=0 ; i<ba2.size();i++){
                    g.drawImage(ba2.get(i).getImage(),ba2.get(i).getX(),ba2.get(i).getY(),100,100,this);
		}
		for(int i=0 ; i<fireball.size();i++){
		    for(int j=0 ; j<ba2.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba2.get(j).getbound())){
			    ba2.remove(j);
			    fireball.remove(i);
			    
                            times += 10;
			    g.drawString("+20",m.x+100,650);
   			} 
		    }
		}
                
                
		//===========ball5================
                
		for(int i=0 ; i<ba5.size();i++){
		    g.drawImage(ba5.get(i).getImage(),ba5.get(i).getX(),ba5.get(i).getY(),100,100,this);
		}
		for(int i=0 ; i<fireball.size();i++){
                    for(int j=0 ; j<ba5.size();j++){
                        if(Intersect(fireball.get(i).getbound(),ba5.get(j).getbound())){
                            ba5.remove(j);
			    fireball.remove(i);
			    scor -=20;
                            times -=5;
			    HP=HP-1;
			    g.drawString("-1HP",m.x+100,580);
			    g.drawString("-20",m.x+100,650);
			} 
		    }
		}
		      
		g.drawImage(imgtemp.getImage(),0,0,1000,800,this);
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.setColor(Color.white);
		g.drawString("SCORE",445, 55);
                g.setFont(new Font("Mitr",Font.PLAIN,70));
                if (scor == 0){
                    g.drawString(""+scor,470, 115);
                }
                else if (scor>=10){
                    g.drawString(""+scor,450, 115);
                }
                else if (scor<=-1){
                    g.drawString(""+scor,440, 115);
                }
                else{
                    g.drawString(""+scor,470, 115);
                }
                
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.drawString("ROUND : "+rs2,140,45);
		g.drawString("Time :  "+times,140,85);
		
                if (HP == 3){
                    g.drawImage(imgHP3.getImage(),40,20,30,70,this);
                }
                else if (HP == 2){
                    g.drawImage(imgHP2.getImage(),40,20,30,70,this);
                }
                else if (HP == 1){
                    g.drawImage(imgHP1.getImage(), 40, 20, 30, 70, this);
                }
		      

                
            }else{
                g.drawImage(imgstate1.getImage(),0,0,1000,800,this);
                
                if(paralyze1){
                    g.setColor(Color.RED);
                    g.setFont(new Font("Hobo Std",Font.BOLD,50));
                    
                    g.drawString("-10",m.x+100,650);
                    g.drawString("AHHHH !!!", m.x+100, 560);
                }else{
                    
                    g.drawImage(m.k.getImage(), m.x, 550,200,200, this);
                    
                }
		if(m.x<-90){
                    m.x=this.getWidth()-10;
		}
		if(m.x>this.getWidth()){
                    m.x=-90;
		}
		for(int i=0;i<fireball.size();i++){
		    Fireball ba = fireball.get(i);
                    g.drawImage(ba.imfire.getImage(), ba.x+80, ba.y,50,50, null);
		    ba.move();
                    ba.count++;
		    if(ba.y<0){
		    	fireball.remove(i);
		    }
		}
                
		  
                
		//========================================ball1=================
                
                for(int i=0 ; i<ba1.size();i++){
                    g.drawImage(ba1.get(i).getImage(),ba1.get(i).getX(),ba1.get(i).getY(),100,100,this);
 		}
		for(int i=0 ; i<fireball.size();i++){
		    for(int j=0 ; j<ba1.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba1.get(j).getbound())){
                           
			    ba1.remove(j);
			    fireball.remove(i);
			    scor += 10;
                           
			    g.drawString("+10",m.x+100,650);
			} 
		    }
		}
                
                
                
		//========================ball2=========================
                
		for(int i=0 ; i<ba2.size();i++){
		    g.drawImage(ba2.get(i).getImage(),ba2.get(i).getX(),ba2.get(i).getY(),100,100,this);
		 }
		for(int i=0 ; i<fireball.size();i++){
		    for(int j=0 ; j<ba2.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba2.get(j).getbound())){
			    ba2.remove(j);
			    fireball.remove(i);
                            times += 10;
			    
                            
                            
			    g.drawString("+10",m.x+100,650);
			 } 
		    }
		}
                
                
		//=================================ball5=============
                
		for(int i=0 ; i<ba5.size();i++){
		    g.drawImage(ba5.get(i).getImage(),ba5.get(i).getX(),ba5.get(i).getY(),100,100,this);
		}
		for(int i=0 ; i<fireball.size();i++){
		    for(int j=0 ; j<ba5.size();j++){
		    	if(Intersect(fireball.get(i).getbound(),ba5.get(j).getbound())){
			    ba5.remove(j);
			    fireball.remove(i);
			    scor -=20;
                            times -= 10;
			    HP=HP-1;
			    g.drawString("-1HP",m.x+100,650);
			    g.drawString("-20",m.x+100,580);
			} 
                        
                         
		    }
		}
		      
		g.drawImage(imgtemp.getImage(),0,0,1000,800,this);
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.setColor(Color.white);
		g.drawString("SCORE",445, 55);
                g.setFont(new Font("Mitr",Font.PLAIN,70));
                if (scor == 0){
                    g.drawString(""+scor,470, 115);
                }
                else if (scor>=10){
                    g.drawString(""+scor,450, 115);
                }
                else if (scor<=-1){
                    g.drawString(""+scor,440, 115);
                }
                else{
                    g.drawString(""+scor,470, 115);
                }
                
		g.setFont(new Font("Mitr",Font.PLAIN,30));
		g.drawString("ROUND : "+rs1,140,45);
		g.drawString("Time :  "+times,140,85);
		
                if (HP == 3){
                    g.drawImage(imgHP3.getImage(),40,20,30,70,this);
                }
                else if (HP == 2){
                    g.drawImage(imgHP2.getImage(),40,20,30,70,this);
                }
                else if (HP == 1){
                    g.drawImage(imgHP1.getImage(), 40, 20, 30, 70, this);
                }
		      
	    }

	}

	public boolean Intersect(Rectangle2D a, Rectangle2D b){
		return (a.intersects(b));
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BStartover){		
                    this.setSize(1000,800);
                    this.add(hg);
                    this.setLocation(null);
                    timestart = true;
                    startball = true;
		}else if(e.getSource() == BExitover){
                    System.exit(0);
		}		
	}
}
