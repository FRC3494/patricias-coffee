package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.drive.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsytem. Contains all methods for controlling the robot's
 * drivetrain. Also has in instance of RobotDrive (wpiDrive) if you want to use
 * that.
 * 
 * @since 0.0.0
 * @category drivetrain
 * @category subsystems
 */
public class Drivetrain extends Subsystem {
	public Victor drive_left = new Victor(RobotMap.drive_left);
	public Victor drive_right = new Victor(RobotMap.drive_right);
	/**
	 * Instance of wpiDrive for using WPI's driving code. Should <em>not</em> be
	 * used for tank driving (use {@link Drivetrain#TankDrive} instead.)
	 * 
	 * @since 0.0.0
	 */
	public RobotDrive wpiDrive = new RobotDrive(drive_left, drive_right);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Drivetrain() {
		super("Drivetrain");
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}

	/**
	 * Drives the drivetrain tank drive style. The drivetrain will continue to
	 * run until stopped with a method like {@link Drivetrain#StopDrive()}.
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
		if (left > RobotMap.DRIVE_TOLERANCE && right > RobotMap.DRIVE_TOLERANCE) {
			drive_left.set(left);
			drive_right.set(right);
		}
	}

	/**
	 * Stops all drive motors. Does not require re-enabling motors after use.
	 * 
	 * @since 0.0.0
	 */
	public void StopDrive() {
		drive_left.stopMotor();
		drive_right.stopMotor();
	}
}
