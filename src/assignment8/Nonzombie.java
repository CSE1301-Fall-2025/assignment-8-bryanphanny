package assignment8;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import java.awt.Color;

public class Nonzombie extends Entity {

	public static final double NONZOMBIE_SPEED = 0.01;

	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Nonzombie(double x, double y) {
		// FIXME
		super(x, y, false, NONZOMBIE_SPEED);
	}
	
	/**
	 * Create a Zombie object in place of the current Nonzombie 
	 * @return the new Zombie object
	 */
	public Zombie convert() {
		this.wasConsumed();
		Zombie newZombie = new Zombie(this.getX(), this.getY());
		return newZombie;
	}
	
	/**
	 * Draw a Nonzombie
	 */
	public void draw() {
		if (this.isAlive()) {
			StdDraw.setPenColor(Color.PINK);
			StdDraw.filledCircle(this.getX(), this.getY(), this.getRadius());

		}
	}

	/**
	 * Update the Nonzombie
	 * @param entities the array of Entity objects in the simulation, consumed or not
	 * @return the new Entity object to take the place of the current one
	 */
	public Entity update(Entity[] entities) {
		if (!this.isAlive()) {
			return this;
		}
		
		Entity closest = this.findClosestEntity(entities);
		double zombieThreshold = 0.80;
		if (closest != null) {
			if (closest.isZombie()) {
				Zombie closestZombie = (Zombie)closest;
				this.moveAwayFrom(closestZombie);
				if (this.isTouching(closestZombie)) {
					if (Math.random() < zombieThreshold) {
						return this.convert();
					}
					else {
						this.wasConsumed();
						closestZombie.consumeNonzombie();
					}
				}
			}
			else {
				this.moveToward(closest);
			}
		}
		this.checkBounds();
		return this;
	}


}
