package A3_GAME;

import javax.swing.*;
import java.awt.event.*;

public class Missile extends Sprite implements ActionListener {

	Timer t;
    private double Velx, Vely;

    public Missile(double x, double y, double Velx, double direct, boolean visible) {
		super(100, 800, 20, 20, visible);
		t = new Timer(50, this);
		t.start();
		//this.Velx = Velx;
		//this.Vely = Vely;
    }

	public Missile(double x, double y, double Vel, double direct)
	{
		super(x, y, 20, 20);
		t = new Timer(50, this);
		t.start();
		System.out.println(direct);
		this.Velx = Math.sin(Math.toRadians(direct)) * Vel;
		this.Vely = Math.cos(Math.toRadians(direct)) * Vel;
		System.out.println("Velx" + Velx + "Vely" + Vely);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.x += Velx; this.y -= Vely;
		//System.out.println("missile" + x + " " + y);
		//t.start();
	}

	public static void main(String[] args)  {
		new Missile(0, 0, 0, 0, true);

		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
   