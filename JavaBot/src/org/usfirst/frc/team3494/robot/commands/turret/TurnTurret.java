package org.usfirst.frc.team3494.robot.commands.turret;

import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTurret extends Command {

	public TurnTurret() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("TurnTurret");
		requires(Robot.turretRing);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.xbox.getXButton()) {
			Robot.turretRing.turnTurret(DriveDirections.LEFT);
		} else if (Robot.oi.xbox.getBButton()) {
			Robot.turretRing.turnTurret(DriveDirections.RIGHT);
		} else {
			Robot.turretRing.turnTurret(DriveDirections.STOP);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
