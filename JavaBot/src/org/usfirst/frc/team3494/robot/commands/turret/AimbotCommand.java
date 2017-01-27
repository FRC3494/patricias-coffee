package org.usfirst.frc.team3494.robot.commands.turret;

import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimbotCommand extends Command {

	public AimbotCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.turretRing);
		requires(Robot.memSys);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double centerX = Robot.memSys.getCenterX();
		double centerDist = centerX - (Robot.getImgWidth() / 2);
		if (centerX > 75 || centerX < -75) {
			if (centerDist < 0) {
				Robot.turretRing.turnTurret(DriveDirections.LEFT);
			} else if (centerDist > 0) {
				Robot.turretRing.turnTurret(DriveDirections.RIGHT);
			}
		} else if (centerDist != 0) {
			if (centerDist < 0) {
				Robot.turretRing.preciseTurret(0.01, DriveDirections.LEFT);
			} else if (centerDist > 0) {
				Robot.turretRing.preciseTurret(0.01, DriveDirections.RIGHT);
			}
		} else {
			Robot.turretRing.turnTurret(DriveDirections.STOP);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}