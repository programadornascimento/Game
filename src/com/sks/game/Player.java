package com.sks.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import com.sks.gf.CollisionManager;
import com.sks.gf.GameManager;
import com.sks.gf.GameObject;

public class Player extends GameObject {
	int health, lastStep;
	double vx, vy, friction, bounce, speed;
	boolean vxap, vyap, vxam, vyam, timeForward, destroy;
	Vector<Integer> pathX = new Vector<Integer>();
	Vector<Integer> pathY = new Vector<Integer>();

	public Player() {
		drawn = true;
		health = 100;
		x = 0;
		y = 0;
		size = 20;
		vx = 0;
		vy = 0;
		friction = 1.0;
		bounce = 0.3;
		speed = 1.5;
		type = "Player";
		timeForward = true;
		lastStep = 0;
	}

	public void paint(Graphics g) {
		if (!destroy)
		{
			g.fillRect(x, y, size, size);
		} else {
			for (int i=0; i < 100; i++)
			{
				g.drawOval(x+(int)((step-lastStep)*Math.cos((double)2*i/76.0*(Math.PI))), y+(int)((step-lastStep)*Math.sin((double)2*i/76.0*(Math.PI))), 5, 5);
			}
		}
	}
	
	public void destroy() {
		destroy = true;
		lastStep = step;
	}

	public void customLogic(GameManager gm, CollisionManager cm) {
		x += (int) vx;
		y += (int) vy;
		if (vxap) {
			vx += speed;
		}
		if (vxam) {
			vx -= speed;
		}
		if (vyap) {
			vy += speed;
		}
		if (vyam) {
			vy -= speed;
		}
		if (vx > 0) {
			vx -= friction;
		}
		if (vy > 0) {
			vy -= friction;
		}
		if (vx < 0) {
			vx += friction;
		}
		if (vy < 0) {
			vy += friction;
		}
		if (y > 500) {
			y = 500;
			vy = vy * (-bounce);
		}
		if (y < 0) {
			y = 0;
			vy = vy * (-bounce);
		}
		if (x > 520) {
			x = 520;
			vx = vx * (-bounce);
		}
		if (x < 0) {
			x = 0;
			vx = vx * (-bounce);
		}
		
		if (destroy && (lastStep - step) > 50)
		{
			gm.removeGameObject(id);
		}
		
		if (cm.getCollision(gm.gameObjects, id, "Power Up"))
		{
			destroy();
		}
	}

	public void keyEvent(KeyEvent e, GameManager gm) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == 37) {
				vxam = true;
			}
			if (e.getKeyCode() == 38) {
				vyam = true;
			}
			if (e.getKeyCode() == 39) {
				vxap = true;
			}
			if (e.getKeyCode() == 40) {
				vyap = true;
			}
			if (e.getKeyCode() == 32) {
				gm.addGameObject(new Bullet(x, y));
				vx -= 10;
			}
			if (e.getKeyCode() == 16) {
				gm.fps.stop();
			}
			if (e.getKeyCode() == 90) {
				gm.timeForward = false;
			}
		}
		if (e.getID() == KeyEvent.KEY_RELEASED) {
			if (e.getKeyCode() == 37) {
				vxam = false;
			}
			if (e.getKeyCode() == 38) {
				vyam = false;
			}
			if (e.getKeyCode() == 39) {
				vxap = false;
			}
			if (e.getKeyCode() == 40) {
				vyap = false;
			}
			if (e.getKeyCode() == 16) {
				gm.fps.start();
			}
			if (e.getKeyCode() == 90) {
				gm.timeForward = true;
			}
		}
	}
}
