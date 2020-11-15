package javaapplication7;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class homegames extends JPanel{
        private ImageIcon imglogo = new ImageIcon(this.getClass().getResource("logo_F.gif"));
        private ImageIcon feild = new ImageIcon(this.getClass().getResource("home_bg.gif"));
	private ImageIcon imgship = new ImageIcon(this.getClass().getResource("ship3.gif"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("but_exit.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("but_start.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
	homegames(){
            setLayout(null);
            BExit1.setBounds(450, 670, 100,40);
            add(BExit1);
            add(BStart);
            BStart.setBounds(400,550,200,100);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,1000,800,this);
            g.drawImage(imglogo.getImage(), 200, 60, 600, 350, this);         
            g.drawImage(imgship.getImage(), 580, 380, 400, 400, this);
            
         	
	}
}