package com.sks.gf;

import java.awt.Container;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

import javax.swing.JFrame;

public class AppManager extends JFrame {
	private static final long serialVersionUID = 1L;
	GameArea ga;
	GameManager gm;

	public AppManager() {
		setVisible(true);
		setSize(533, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Make frame non-resizeable
	}

	public static void main(String[] args) {
		GameArea ga = new GameArea();
		CollisionManager cm = new CollisionManager();
		final GameManager gm = new GameManager(ga, cm);
		AppManager am = new AppManager();

		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventPostProcessor(new KeyEventPostProcessor() {

					public boolean postProcessKeyEvent(KeyEvent e)
							throws ConcurrentModificationException {
						// System.out.println(e.getKeyCode());
						try {
							ListIterator<GameObject> iter = gm.gameObjects
									.listIterator();
							while (iter.hasNext()) {
								((GameObject) iter.next()).keyEvent(e, gm);
							}
						} catch (Exception ex) {
							System.out.println("KEY EXCEPTION: "
									+ ex.toString());
						}
						return true;
					}
				});

		Container c = am.getContentPane();
		c.add(ga);
	}
}
