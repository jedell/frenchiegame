package com.tut.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static float HEARTS = 0f; 
    
    private float blueValue = 255;
    private static float LEVEL = 1f;

    public void tick() {
	
	HEARTS = Game.clamp(HEARTS, 0, 100);
	blueValue = Game.clamp(blueValue, 0, 255);
	
	blueValue = HEARTS*2;
    }
    
    public void render(Graphics g) {
	g.setColor(Color.black);
	g.fillRect(15, 15, 100, 16);
	g.setColor(new Color(200, 0, (int) blueValue));
	g.fillRect(15, 15, (int) HEARTS, 16);
	g.setColor(Color.white);
	g.drawRect(15, 15, 100, 16);

    }
    
    public void hearts(float hearts) {
	this.HEARTS = hearts;
    }
    
    public float getHearts() {
	return HEARTS;
    }
    
    public static float getLevel() {
	return LEVEL;
    }
    
    public static void setLevel(float level) {
	LEVEL = level;
    }
}
