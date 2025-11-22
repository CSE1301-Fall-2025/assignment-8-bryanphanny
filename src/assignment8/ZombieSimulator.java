package assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class ZombieSimulator {
	private static final String ZOMBIE_TOKEN_VALUE = "Zombie";
	private Entity[] simulator;


	/**
	 * Constructs a ZombieSimulator with an empty array of Entities.
	 */
	public ZombieSimulator(int n) {
		this.simulator = new Entity[n];
	}

	/**
	 * @return the current array of entities 
	 */
	public Entity[] getEntities() {
		// FIXME
		return this.simulator;
	}

	/** 
	 * Reads an entire zombie simulation file from a specified ArgsProcessor, adding
	 * each Entity to the array of entities.
	 *
	 * Assume that N (the integer indicating how many entities are in the simulation) has already been read in
	 * and passed into the constructor.
	 *
	 * @param in Scanner to read the complete zombie simulation file format.
	 */
	public void readEntities(Scanner in) {
		// FIXME
		for (int i = 0; i < this.simulator.length; i++) {
			String entity = in.next();
			double x = in.nextDouble();
			double y = in.nextDouble();

			Entity entityToPlace = null;
			if (entity.equals(ZOMBIE_TOKEN_VALUE)) {
				entityToPlace = new Zombie(x, y);
			}
			else {
				entityToPlace = new Nonzombie(x, y);
			}
			this.simulator[i] = entityToPlace;
		}
	}

	/**
	 * @return the number of zombies in entities.
	 */
	public int getZombieCount() {
		// FIXME
		int zombieCount = 0;
		for (Entity entity : this.simulator) {
			if (entity != null && entity.isAlive() && entity.isZombie()) {
				zombieCount++;
			}
		}
		return zombieCount;
	}

	/**
	 * @return the number of nonzombies in entities.
	 */
	public int getNonzombieCount() {
		int nonZombieCount = 0;
		for (Entity entity : this.simulator) {
			if (entity != null && entity.isAlive() && !entity.isZombie()) {
				nonZombieCount++;
			}
		}
		return nonZombieCount;
	}

	/**
	 * Draws a frame of the simulation.
	 */
	public void draw() {
		StdDraw.clear();

		// NOTE: feel free to edit this code to support additional features
		for (Entity entity : getEntities()) {
			entity.draw();
		}

		StdDraw.show(); // commit deferred drawing as a result of enabling double buffering
	}

	/**
	 * Updates the entities for the current frame of the simulation given the amount
	 * of time passed since the previous frame.
	 * 
	 * Note: some entities may be consumed and will not remain for future frames of
	 * the simulation.
	 * 
	 */
	public void update() {
		// FIXME
		for (int i = 0; i < this.simulator.length; i ++) {
			Entity currentEntity = this.simulator[i];
			if (currentEntity != null && currentEntity.isAlive()) {
				Entity returnedEntity = currentEntity.update(this.simulator);
				this.simulator[i] = returnedEntity;
			}
		}	
	}

	/**
	 * Runs the zombie simulation.
	 * 
	 * @param args arguments from the command line
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up

		JFileChooser chooser = new JFileChooser("zombieSims");
		chooser.showOpenDialog(null);
		File f = new File(chooser.getSelectedFile().getPath());
		Scanner in = new Scanner(f); //making Scanner with a File

		ZombieSimulator zombieSimulator = new ZombieSimulator(in.nextInt());
		zombieSimulator.readEntities(in);

		while (zombieSimulator.getNonzombieCount() >= 0) {

			zombieSimulator.update();
			zombieSimulator.draw();

			StdDraw.pause(20);

		}
	}
}
