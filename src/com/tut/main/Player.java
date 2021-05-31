package com.tut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject {
    
    private BufferedImage image;
    Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
	super(x, y, id);
	this.handler = handler;
	
	try {
	    image = ImageIO.read(new File("frenchie.png"));
	} catch (IOException e) {
	}
	
    }
    
    public Rectangle getBounds() {
	return new Rectangle((int)x-22,(int)y-31,51,56);
    }

    public void tick() {
	x += velX;
	y += velY;
	
	x = Game.clamp(x, 24, Game.WIDTH-32);
	y = Game.clamp(y, 35, Game.HEIGHT-52);
	
	collision();
    }
    
    private void collision() {
	for (int i = 0; i<handler.object.size();i++) {
	    
	    GameObject tempObject = handler.object.get(i);
	    
	    if (tempObject.getId()== ID.BasicEnemy) {
		
		//collision stuff
		if(getBounds().intersects(tempObject.getBounds())) {
		    HUD.HEARTS += 0;
		}
	    }
	}
    }

    public void render(Graphics g) {
	
	//Graphics2D g2d = (Graphics2D) g;
	
	//g.setColor(Color.green);
	//g2d.draw(getBounds());
	
	//drawPug(g, x, y);
	g.drawImage(image,(int) x-42, (int)y-50, null);
	
    }
    
    
    
 
    
}
