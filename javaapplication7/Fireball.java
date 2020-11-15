package javaapplication7;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fireball extends JPanel{
    public ImageIcon imfire = new ImageIcon(this.getClass().getResource("rocket02.gif"));;
    public int y;
    public int x;
    public int count=0;
    Fireball(int x,int y){
        
            this.x=x;
            this.y=y;
    }
	
    public void move(){
	this.y-=10;
        
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x+80,y,25,25));
    }
}
