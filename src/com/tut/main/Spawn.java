package com.tut.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tut.main.Game.STATE;

public class Spawn {
    
    private Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();
    public static int level = 1;
    
    public Spawn(Handler handler, HUD hud) {
	this.handler = handler;
	this.hud = hud;
	
    }
    
    public void tick() {
	//1
	if (level == 1) {
	    	    
	    for(int i = 0; i < 10; i++) {
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
		}	    
	    HUD.HEARTS = 0;
	    level++;
	}
	//2
	if (HUD.HEARTS == 100 && level == 2) {
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
	
	    }
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
		}
	    HUD.HEARTS = 0;
	    level++;
	}
	//3
	if (HUD.HEARTS == 100 && level == 3) {
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
	
	    }
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
		}
	    HUD.HEARTS = 0;
	    level++;
	}
	//4
	if (HUD.HEARTS == 100 && level == 4) {
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
	
	    }
	    for(int i = 0; i < 5; i++) {
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
		}
	    for(int i = 0; i < 6; i++) {
		handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
	
	    }
	    HUD.HEARTS = 0;
	    level++;
	}
	
	
	
    }
    
    public int getLevel() {
	return level;
    }
}
