package com.sks.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sks.gf.CollisionManager;
import com.sks.gf.GameManager;
import com.sks.gf.GameObject;

public class Bullet extends GameObject {
	boolean destroy;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		size = 10;
		type = "Bullet";
	}

	@Override
	public void paint(Graphics g) {
		g.fillOval(x, y, size, size);
	}

	public void destroy() {
		destroy = true;
	}

	@Override
	public void customLogic(GameManager gm, CollisionManager cm) {
		x += 5;
		
		if (cm.getCollision(gm.gameObjects, id, "Power Up"))
		{
			destroy();
		}
		
		if (destroy) {
			gm.removeGameObject(id);
		}
	}

	@Override
	public void keyEvent(KeyEvent e, GameManager gm) {
		// TODO Auto-generated method stub

	}

}
