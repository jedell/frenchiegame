package com.tut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
    
    private Handler handler;
    
    public BasicEnemy(float x, float y, ID id, Handler handler) {
	super(x, y, id);
	this.handler = handler;
	
	velX = 4;
	velY = 4;
    }
    
    public Rectangle getBounds() {
	return new Rectangle((int)x,(int)y,16,16);
	
       }


    
    public void tick() {
	x += velX;
	y += velY;
	
	if (y <= 0 || y >= Game.HEIGHT - 32) velY*=-1;
	if (x <= 0 || x >= Game.WIDTH - 16) velX*=-1;
	
	handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));
	
	collision();
    }
    
    private void collision() {
	for (int i = 0; i<handler.object.size();i++) {
	    
	    GameObject tempObject = handler.object.get(i);
	    
	    if (tempObject.getId()== ID.Player) {
		
		//collision stuff
		if(getBounds().intersects(tempObject.getBounds())) {
		    HUD.HEARTS += 10;
		    handler.removeObject(this);
		}
	    }
	}
    }

    public void render(Graphics g) {
	
	 g.setColor(Color.red);
	 drawHeart(g, (int)x, (int)y, 16, 16);
	
    }
    
    
    
    
    
    
    public void drawHeart(Graphics g, int x, int y, int width, int height) {
	    int[] triangleX = {
	            x - 2*width/18,
	            x + width + 2*width/18,
	            (x - 2*width/18 + x + width + 2*width/18)/2};
	    int[] triangleY = { 
	            y + height - 2*height/3, 
	            y + height - 2*height/3, 
	            y + height };
	    g.fillOval(
	            x - width/12,
	            y, 
	            width/2 + width/6, 
	            height/2); 
	    g.fillOval(
	            x + width/2 - width/12,
	            y,
	            width/2 + width/6,
	            height/2);
	    g.fillPolygon(triangleX, triangleY, triangleX.length);
	}

}
