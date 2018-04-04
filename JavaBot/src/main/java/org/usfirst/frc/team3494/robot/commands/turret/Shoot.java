package org.usfirst.frc.team3494.robot.commands.turret;

import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Default command for shooter
 */
public class Shoot extends Command {

	public Shoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.turret);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.oi.xbox.getTriggerAxis(Hand.kRight) > 0) {
			Robot.turret.shoot(Robot.oi.xbox.getTriggerAxis(Hand.kRight) * Robot.prefs.getDouble("shootlim", 1));
		} else {
			Robot.turret.shoot(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
