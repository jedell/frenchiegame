package com.tut.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tut.main.Game.STATE;

public class Menu extends MouseAdapter{
    
    private BufferedImage playimage;
    private BufferedImage quitimage;
    private BufferedImage tagimage;
    private BufferedImage winimage;

    private Game game;
    private Handler handler;
    
    public Menu(Game game, Handler handler) {
	this.game = game;
	this.handler = handler;
	
	try {
	    playimage = ImageIO.read(new File("playbutton.png"));
	} catch (IOException e) {
	}
	try {
	    quitimage = ImageIO.read(new File("quitbutton.png"));
	} catch (IOException e) {
	}
	try {
	    tagimage = ImageIO.read(new File("tagline.png"));
	} catch (IOException e) {
	}
	try {
	    winimage = ImageIO.read(new File("winscreen.png"));
	} catch (IOException e) {
	}
    }
    
    public void mousePressed(MouseEvent e) {
	int mx = e.getX();
	int my = e.getY();
	
	
	//play button
	if (mouseOver(mx, my, 160, 98, 331, 131)) {
	    game.gameState = STATE.Game;
	    handler.addObject(new Player(Game.WIDTH/2-32+24, Game.HEIGHT/2-32+16, ID.Player, handler));

	}
	
	//quit button
	if (mouseOver (mx, my, 155, 233, 341, 131)) {
	    System.exit(1);
	}
    }
    
    public void mouseReleased(MouseEvent e) {
	
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
	if (mx > x && mx < x + width) {
	    if (my > y && my < y + height) {
		return true;
	    } else return false;
	} else return false;
    }
    
    public void tick() {
	
    }
    
    public void render(Graphics g) {
	if (game.gameState == STATE.Menu) {
    	g.setColor(Color.WHITE);
    	//start hit box
    	//g.drawRect(160, 98, 331, 131);
    	g.drawImage(playimage, Game.WIDTH/6 + 120, Game.HEIGHT/6 + 50, null);
    	
    	g.setColor(Color.WHITE);
    	//quit hit box
    	//g.drawRect(155, 233, 341, 131);
    	 
    	g.drawImage(quitimage, Game.WIDTH/6 +150, Game.HEIGHT/6 +155, null);
    	
    	g.drawImage(tagimage, Game.WIDTH/6 , Game.HEIGHT/6 -20, null);
	} else if (game.gameState == STATE.End) {
	    g.drawImage(winimage, Game.WIDTH/6 -80, Game.HEIGHT/6 -20, null);

	}
	
	
    }
}
