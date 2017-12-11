package org.usfirst.frc.team3494.robot.commands.lifter;

import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Lift based on values passed in on construction. Good for auto programs(?)
 */
public class AutoLift extends Command {

	private boolean up;

	public AutoLift(boolean up) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.lifter);
		this.up = up;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.lifter.lift(this.up);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return !Robot.oi.getXbox_a().get();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.lifter.stopLift();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
