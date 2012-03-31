package com.sks.gf;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

public abstract class GameObject {
	public int id = (int) Math.round(Math.random() * 100000);
	protected boolean drawn = true;
	public int x, y, size, step = 0;
	protected double vx;
	protected double vy;
	public String type;
	Vector<Integer> pathX = new Vector<Integer>();
	Vector<Integer> pathY = new Vector<Integer>();

	public void destroy() {
		
	}

	public abstract void paint(Graphics g);

	public abstract void keyEvent(KeyEvent e, GameManager gm); // TODO: GET RID
																// OF GM,
																// MAYBE? NAH I
																// LOVE IT

	public void logic(boolean timeForward, GameManager gm, CollisionManager cm) {
		if (timeForward) {
			customLogic(gm, cm);
			pathX.add(x);
			pathY.add(y);
			step++;
		} else {
			if (step > 0) {
				step--;
				x = pathX.elementAt(step);
				y = pathY.elementAt(step);
				pathX.removeElementAt(step);
				pathY.removeElementAt(step);
			}
		}
	}

	public abstract void customLogic(GameManager gm, CollisionManager cm);

	public String toString() {
		String string = "Object id " + Integer.toString(id) + " of type "
				+ type;
		return string;
	}
}
