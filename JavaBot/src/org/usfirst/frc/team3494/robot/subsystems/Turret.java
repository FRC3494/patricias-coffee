package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.commands.turret.Shoot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	private Victor shooter_top = new Victor(RobotMap.shooterTop);
	private Victor shooter_bot = new Victor(RobotMap.shooterBottom);
	private Victor turret_con = new Victor(RobotMap.turretMotor);
	private Encoder turret_enc = new Encoder(8, 9);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Shoot());
	}

	/**
	 * Run the shooter.
	 * 
	 * @param speed
	 *            The speed to run the shooter at. This can be backwards,
	 *            but...why?
	 */
	public void shoot(double speed) {
		shooter_top.set(speed);
		shooter_bot.set(speed);
	}

	/**
	 * Turn the turret.
	 * 
	 * @param dir
	 *            The direction to turn in. Please oh please only use
	 *            {@link DriveDirections#LEFT}, {@link DriveDirections#RIGHT} or
	 *            {@link DriveDirections#STOP}
	 */
	public void turnTurret(DriveDirections dir) {
		if (dir.equals(DriveDirections.LEFT)) {
			turret_con.set(0.5);
		} else if (dir.equals(DriveDirections.RIGHT)) {
			turret_con.set(-0.5);
		} else {
			turret_con.stopMotor();
		}
	}

	/**
	 * @return the turret_enc
	 */
	public Encoder getTurret_enc() {
		return turret_enc;
	}
}
