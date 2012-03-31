package com.sks.gf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.Timer;

import com.sks.game.GlobalGameObject;
import com.sks.game.Player;
import com.sks.game.PowUp;

public class GameManager implements ActionListener {
	GameManager gm = this;
	boolean objectListLocked, objectStatus;
	public boolean timeForward;
	CollisionManager cm;
	public Vector<GameObject> gameObjects = new Vector<GameObject>();
	GameArea ga;
	int state;
	public Timer fps;
	Player player;
	PowUp powUp;

	public GameManager(GameArea ga, CollisionManager cm) {
		timeForward = true;
		this.ga = ga;
		this.cm = cm;
		fps = new Timer(1000 / 30, this);
		fps.addActionListener(this);
		player = new Player();
		powUp = new PowUp(100, 100, 50);
		GlobalGameObject global = new GlobalGameObject(cm, gameObjects);

		addGameObject(global);
		addGameObject(player);
		addGameObject(powUp);
		/*addGameObject(new Bullet(0,100));
		for(int i = 0; i<10; i++)
		{
		addGameObject(new PowUp(Math.random()*100+50, Math.random()*100+50, Math.random()*30+20));
		}*/
		fps.start();
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if ("Quit".equals(ac)) {
			System.exit(0);
		}
		if (e.getSource() == fps) {
			// Logic Functions
			ListIterator<GameObject> logicIter = gameObjects.listIterator();
			while (logicIter.hasNext()) {
				((GameObject) logicIter.next()).logic(timeForward, this, cm);
			}
			ga.repaint();

			// Collision Code, maybe move out of GameManager? idk

			if (cm.getCollision(gameObjects, player.id, powUp.id)) {
				System.out.println("COLLISION between a powup and a player");
			}

			for (int i = 0; i < gameObjects.size(); i++)
			{
				if (gameObjects.elementAt(i).type.equals("Bullet"))
				{
					for (int j = 0; j < gameObjects.size(); j++)
					{
						if (gameObjects.elementAt(j).type.equals("Power Up"))
						{
							if (cm.getCollision(gameObjects, gameObjects.elementAt(j).id, gameObjects.elementAt(i).id)) {
								//(gameObjects.elementAt(j)).vx += Math.random() * 5 + 5;
								//(gameObjects.elementAt(j)).vy += Math.random() * 10 - 5;
								gameObjects.elementAt(j).destroy();
								gameObjects.elementAt(i).destroy();
							}
						}
					}
				}
			}
		}
	}

	public void addGameObject(GameObject object) throws ConcurrentModificationException {
		try {
			if (object.drawn) {
				ga.addDrawItem(object);
			}
			gameObjects.add(object); // TODO: AppManager accessing gameObject
										// Vector at the same time as add/remove
										// game object
			System.out.println(object.toString() + " Game Object added");
		} catch (Exception ex) {
			System.out.println("AGO EXEPTION: " + ex.toString());
		}
	}

	public void removeGameObject(long removeId)
			throws ConcurrentModificationException {
		// if(removeId != 100000) {
		try {
			ListIterator<GameObject> iter = gameObjects.listIterator();
			while (iter.hasNext()) {
				if (((GameObject) iter.next()).id == removeId) {
					iter.remove();
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("RGO EXEPTION: " + ex.toString());
		}
		// } else {
		// System.out.println("GlobalGameObject must not be removed!");
		// }
		ga.removeDrawItem(removeId);
		System.out.println("REMOVED object "+removeId);
	}

}
