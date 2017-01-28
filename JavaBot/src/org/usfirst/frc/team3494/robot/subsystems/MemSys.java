package org.usfirst.frc.team3494.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * hacky way of storing some stuff
 * @since 0.0.0
 */
public class MemSys extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private double centerX;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setCenterX(double set) {
		this.centerX = set;
	}

	public double getCenterX() {
		return this.centerX;
	}
}
