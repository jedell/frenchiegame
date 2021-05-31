package com.tut.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tut.main.Game.STATE;

public class Game extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 8845325147047547333L;

    public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    
    
    public enum STATE {
	Menu,
	End,
	Game
    };
    
    public STATE gameState = STATE.Menu;
    
    public Game() {
	handler = new Handler();
	menu = new Menu(this, handler);
	this.addKeyListener(new KeyInput(handler));
	this.addMouseListener(menu);
	
	
	new Window( (int) WIDTH, (int) HEIGHT, "French Bulldog Game", this);
	
	hud = new HUD();
	spawner = new Spawn(handler, hud);
	
	r = new Random();
	
	if (gameState == STATE.Game) {
	    handler.addObject(new Player(WIDTH/2-32+24, HEIGHT/2-32+16, ID.Player, handler));
	    //for(int i = 0; i < 10; i++) {
	    //handler.addObject(new BasicEnemy(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-32), ID.BasicEnemy, handler));
	    //}	    
	}
	
	
	

    }
    
    public synchronized void start() {
	thread = new Thread(this);
	thread.start();
	running = true;
    }
    
    public synchronized void stop() {
	try {
	    thread.join();
	    running = false;
	    
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void run() {
	this.requestFocus();
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int frames = 0;
	while(running) {
	    long now = System.nanoTime();
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    while (delta >= 1) {
		tick();
		delta--;
	    }
	    if (running)
		render();
	    frames++;
	    
	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		//System.out.println("FPS: " + frames);
		frames = 0;
	    }
	}
	stop();
    }
    
    private void tick() {
	handler.tick();
	
	if (gameState == STATE.Game) {
	    hud.tick();
	    spawner.tick();
	    
	    if (Spawn.level == 5 && HUD.HEARTS >= 100) {
		    gameState = STATE.End;
		}
	} else if (gameState == STATE.Menu || gameState == STATE.End) {
	    menu.tick();
	}
	
	
    }
    
    private void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null) {
	    this.createBufferStrategy(3);
	    return;
	}
	
	Graphics g = bs.getDrawGraphics();
	
	g.setColor(Color.pink);
	g.fillRect(0, 0, (int) WIDTH, (int) HEIGHT);
	
	
	handler.render(g);
	
	if (gameState == STATE.Game) {
	    hud.render(g);

	} else if (gameState == STATE.Menu || gameState == STATE.End) {
	    menu.render(g);
	} 
	
	
	g.dispose();
	bs.show();
    }
    
    public static float clamp(float var, float min, float max) {
	if(var >= max) {
	    return var = max;
	} else if (var <= min) {
	    return var = min;
	} else {
	    return var;
	}
    }

    public static void main(String[] args) {
	new Game();
    }

}
