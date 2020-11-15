package javaapplication7;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class ship{
    
    public ImageIcon k  = new ImageIcon(this.getClass().getResource("rocket02.gif"));
    public int x;
    public int y;
    public int count = 0;
    
   public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,250,250));
    }
}
