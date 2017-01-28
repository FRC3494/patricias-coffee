package org.usfirst.frc.team3494.robot.commands.turret;

import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetTurretEnc extends Command {

	public ResetTurretEnc() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.turret);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Resetting turret encoder");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.turret.resetTurret_enc();
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
