package com.sks.gf;

import java.awt.Graphics;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JPanel;

public class GameArea extends JPanel{
	private static final long serialVersionUID = 1L;
	int x, y;
	Vector<GameObject> objects = new Vector<GameObject>();
	
	public GameArea() {
		
	}
	
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ListIterator<GameObject> iter = objects.listIterator();
        while (iter.hasNext()) {
            ((GameObject)iter.next()).paint(g);
        }
    }
	
	public void addDrawItem(GameObject object) {
		objects.add(object);
	}
	
	public void removeDrawItem(long removeId) {
		ListIterator<GameObject> iter = objects.listIterator();
        while (iter.hasNext()) {
            if(((GameObject)iter.next()).id == removeId) {
            	iter.remove();
            	break;
            }
        }
	}
}
