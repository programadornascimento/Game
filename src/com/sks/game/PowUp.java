package com.sks.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sks.gf.CollisionManager;
import com.sks.gf.GameManager;
import com.sks.gf.GameObject;

public class PowUp extends GameObject {
	int lastStep;
	double accel, bounce;
	boolean destroy;

	public PowUp(int x, int y, int size) {
		drawn = true;
		this.x = x;
		this.y = y;
		this.size = size;
		type = "Power Up";
		accel = 0.2;
		bounce = -0.7;
		destroy = false;
	}

	public PowUp(double x, double y, double size) {
		System.out.println(x + " " + y + " " + size);
		drawn = true;
		this.x = (int) x;
		this.y = (int) y;
		this.size = (int) size;
		type = "Power Up";
		accel = -0.2;
	}

	public void paint(Graphics g) {
		if (!destroy) {
			g.fillRect(x, y, size, size);
		} else {
			g.fillRect(x + (step - lastStep) / 2, y + (step - lastStep) / 2,
					size - (step - lastStep), size - (step - lastStep));
		}
	}

	public void customLogic(GameManager gm, CollisionManager cm) {
		x += (int) vx;
		y += (int) vy;
		if (vx > 0) {
			vx -= accel;
		}
		if (vy > 0) {
			vy -= accel;
		}
		if (vx < 0) {
			vx += accel;
		}
		if (vy < 0) {
			vy += accel;
		}
		if (y > 500) {
			y = 500;
			vy = vy * (bounce);
		}
		if (y < 0) {
			y = 0;
			vy = vy * (bounce);
		}
		if (x > 520) {
			x = 520;
			vx = vx * (bounce);
		}
		if (x < 0) {
			x = 0;
			vx = vx * (bounce);
		}
		if (destroy && ((step - lastStep) > size)) {
			gm.removeGameObject(id);
		}
	}

	public void destroy() {
		destroy = true;
		lastStep = step;
	}

	@Override
	public void keyEvent(KeyEvent e, GameManager gm) {
		// TODO Auto-generated method stub

	}
}
