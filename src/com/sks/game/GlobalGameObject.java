package com.sks.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import com.sks.gf.CollisionManager;
import com.sks.gf.GameManager;
import com.sks.gf.GameObject;

public class GlobalGameObject extends GameObject {
	int id = 1000000;
	CollisionManager cm;
	Vector<GameObject> objectList;

	public GlobalGameObject(CollisionManager cm, Vector<GameObject> objectList) {
		drawn = false;
		this.cm = cm;
		type = "Global Game Object";
		this.objectList = objectList;
	}
	
	public void customLogic(GameManager gm, CollisionManager cm) {
		
	}
	
	public void keyEvent(KeyEvent e, GameManager gm) {
		if (e.getKeyCode() == 65) {
			gm.addGameObject(new PowUp(100, 100, (int) (Math.random() * 50)));
		}
		if (e.getKeyCode() == 83) {
			gm.removeGameObject(gm.gameObjects.lastElement().id);
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}