package com.tut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject{
    
    private Handler handler;
    private GameObject player;
    Random r = new Random();
    
    public SmartEnemy(float x, float y, ID id, Handler handler) {
	super(x, y, id);
	this.handler = handler;
	
	for (int i = 0; i< handler.object.size(); i++) {
	    if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
	}
	
	
    }
    
    public Rectangle getBounds() {
	return new Rectangle((int)x,(int)y,32,32);
	
       }


    
    public void tick() {
	x += velX;
	y += velY;
	
	float diffX = x - player.getX() - 12;
	float diffY = y - player.getY() - 12;
	float distance = (float) Math.sqrt( (x-player.getX())*(x-player.getX()) + (y-player.getY()) *(y-player.getY()) );
	
	velX = (float) ((-3.0/distance) * diffX);
	velY = (float) ((-3.0/distance) * diffY);

	
	if (y <= 0 || y >= Game.HEIGHT - 32) velY*=-1;
	if (x <= 0 || x >= Game.WIDTH - 16) velX*=-1;
	
	handler.addObject(new Trail(x, y, ID.Trail, new Color(70, 77, 200), 32, 32, 0.05f, handler));
	
	collision();
    }
    
    private void collision() {
	for (int i = 0; i<handler.object.size();i++) {
	    
	    GameObject tempObject = handler.object.get(i);
	    
	    if (tempObject.getId()== ID.Player) {
		
		//collision stuff
		if(getBounds().intersects(tempObject.getBounds())) {
		    HUD.HEARTS -= 10;
		    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));

		    handler.removeObject(this);
		}
	    }
	}
    }

    public void render(Graphics g) {
	
	 g.setColor(new Color(70, 77, 200));
	 drawHeart(g, (int) x, (int) y, 32, 32);
	
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

