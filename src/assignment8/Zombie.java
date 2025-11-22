package assignment8;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import java.awt.Color;

public class Zombie extends Entity {

	public static final double ZOMBIE_SPEED = 0.011;

	/**
	 * Create a new Zombie object
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Zombie(double x, double y){
		// FIXME
		super(x, y, true, ZOMBIE_SPEED);
	}

	/**
	 * Grow the Zombie after consuming a Nonzombie
	 */
	public void consumeNonzombie(){
		// FIXME
		if (this.getRadius() < 0.02) {
			double newRadius = (this.getRadius() * 0.2) + this.getRadius();
			if (newRadius <= 0.02) {
				this.setRadius(newRadius);
			}
			else {
				this.setRadius(0.02);
			}
		}
	}

	/**
	 * Draw the Zombie
	 */
	public void draw() {
		// FIXME
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledCircle(this.getX(), this.getY(), this.getRadius());
	}


	/**
	 * Update the Zombie
	 * @param entities the array of Entity objects in the simulation, consumed or not
	 * @return the new Entity object to take the place of the current one
	 */
	public Entity update(Entity[] entities) {
		Nonzombie closest = this.findClosestNonzombie(entities);
		if (closest != null) {
			this.moveToward(closest);
		}
		this.checkBounds();
		return this;
	}
}
