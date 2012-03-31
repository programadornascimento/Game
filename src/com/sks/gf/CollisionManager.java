package com.sks.gf;

import java.util.ListIterator;
import java.util.Vector;

public class CollisionManager {
	Vector<GameObject> gameObjects = new Vector<GameObject>();
	int x1, y1, x2, y2, size1, size2;
	GameObject temp;

	public CollisionManager() {
		temp = null;
	}

	public boolean getCollision(Vector<GameObject> objectList, long id1,
			long id2) {
		gameObjects = objectList;
		ListIterator<GameObject> iter = gameObjects.listIterator();
		while (iter.hasNext()) {
			temp = (GameObject) iter.next();
			if (id1 == temp.id) {
				x1 = temp.x;
				y1 = temp.y;
				size1 = temp.size;
			} else if (id2 == temp.id) {
				x2 = temp.x;
				y2 = temp.y;
				size2 = temp.size;
			}
		}
		// THE BELOW IS CORRECT, DO NOT MODIFY!!!
		if (x1 >= x2 - size1 && x1 <= x2 + size2 && y1 >= y2 - size1
				&& y1 <= x2 + size2) {
			return true;
		}
		return false;
	}
	
	public boolean getCollision(Vector<GameObject> objectList, long id1,
			String type) {
		gameObjects = objectList;
		ListIterator<GameObject> iter = gameObjects.listIterator();
		while (iter.hasNext()) {
			temp = (GameObject) iter.next();
			if (id1 == temp.id) {
				x1 = temp.x;
				y1 = temp.y;
				size1 = temp.size;
			} else if (type.equals(temp.type)) {
				x2 = temp.x;
				y2 = temp.y;
				size2 = temp.size;
			}
		}
		
		if (x1 >= x2 - size1 && x1 <= x2 + size2 && y1 >= y2 - size1
				&& y1 <= x2 + size2) {
			return true;
		}
		return false;
	}
}
