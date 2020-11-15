package javaapplication7;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class ball1 {
	Image img;
	public int x = (int) ((Math.random()*300)+20);
	public int y= 0;
        public int speed = 20;
	public int count = 0;
	ball1(){
                String imageLocation = "star02.gif";
                URL imageURL = this.getClass().getResource(imageLocation);
		img = Toolkit.getDefaultToolkit().getImage(imageURL);
		runner.start();
	}
	Thread runner = new Thread(new Runnable() {
            public void run() {
		while(true){
                    y += 2;
                    if(y >= 1100){
			img = null;
			runner = null;
			x = 900;
			y = -500;
                    }
                    try{
			runner.sleep(speed);
                        
                    }catch(InterruptedException e){}
                }
            }
	});
	
	public Image getImage(){
            return img;
	}
	
	public int getX(){
            return x;
	}
	public int getY(){
            return y;
	}
	public Rectangle2D getbound(){
    	    return (new Rectangle2D.Double(x+10,y,45,45));
	}
}
