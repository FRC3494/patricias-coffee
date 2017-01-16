package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.Lift;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lift subsystem
 */
public class Lifter extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Victor lift_bottom = new Victor(RobotMap.liftBottom);
	private Victor lift_top_front = new Victor(RobotMap.liftTopFront);
	private Victor lift_top_back = new Victor(RobotMap.liftTopBack);
	
	public Lifter() {
		super();
		lift_bottom.setInverted(true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Lift());
	}

	/**
	 * Runs the lift.
	 * 
	 * @param up
	 *            Whether the lift should go up. {@code true} for up,
	 *            {@code false} for down.
	 */
	public void lift(boolean up) {
		if (up) {
			lift_bottom.set(0.5);
			lift_top_front.set(0.5);
			lift_top_back.set(0.5);
		} else {
			lift_bottom.set(-0.5);
			lift_top_front.set(-0.5);
			lift_top_back.set(-0.5);
		}
	}

	/**
	 * Stops the lift.
	 */
	public void stopLift() {
		lift_bottom.set(0);
		lift_top_front.set(0);
		lift_top_back.set(0);
	}
}
