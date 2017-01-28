package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.turret.TurnTurret;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretRing extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Victor turret_con = new Victor(RobotMap.turretMotor);
	private Encoder turret_enc;

	public TurretRing() {
		super("TurretRing");
		turret_enc = new Encoder(RobotMap.turretEncoder_A, RobotMap.turretEncoder_B);
		turret_enc.setDistancePerPulse(1 / 420);
		turret_enc.reset();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TurnTurret());
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
			turret_con.set(-0.5);
		} else if (dir.equals(DriveDirections.RIGHT)) {
			turret_con.set(0.5);
		} else {
			turret_con.stopMotor();
		}
	}

	public void preciseTurret(double power, DriveDirections dir) {
		if (dir.equals(DriveDirections.LEFT)) {
			turret_con.set(-power);
		} else if (dir.equals(DriveDirections.RIGHT)) {
			turret_con.set(power);
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

	public void resetTurret_enc() {
		turret_enc.reset();
	}
}
