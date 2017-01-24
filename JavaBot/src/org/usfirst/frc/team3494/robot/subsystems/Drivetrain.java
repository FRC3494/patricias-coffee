package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsytem.
 */
public class Drivetrain extends Subsystem {
	public Victor drive_left = new Victor(RobotMap.drive_left);
	public Victor drive_right = new Victor(RobotMap.drive_right);
	public RobotDrive wpiDrive = new RobotDrive(drive_left, drive_right);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}

	/**
	 * Drives the drivetrain dank drive style.
	 * 
	 * @param left
	 *            The power to drive the left side. Should be a {@code double}
	 *            between 0 and 1.
	 * @param right
	 *            The power to drive the right side. Should be a {@code double}
	 *            between 0 and 1.
	 * @return void
	 */
	public void TankDrive(double left, double right) {
		drive_left.set(left);
		drive_right.set(right);
	}

	/**
	 * Does what it says on the tin.
	 */
	public void StopDrive() {
		drive_left.set(0);
		drive_right.set(0);
	}
}
