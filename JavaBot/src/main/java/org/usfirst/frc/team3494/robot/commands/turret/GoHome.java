package org.usfirst.frc.team3494.robot.commands.turret;

import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.UnitTypes;
import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to go to the encoder 0 position. Stops the turret once it's there.
 * 
 * @see org.usfirst.frc.team3494.robot.subsystems.Turret
 * @see org.usfirst.frc.team3494.robot.subsystems.Turret#getTurretEncDistance
 */
public class GoHome extends Command {

	public GoHome() {
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
		double location = Robot.turret.getTurretEncDistance(UnitTypes.MILLIMETERS);
		if (location > 0) {
			Robot.turret.turnTurret(DriveDirections.LEFT);
		} else {
			Robot.turret.turnTurret(DriveDirections.RIGHT);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.turret.getTurretEncDistance(UnitTypes.MILLIMETERS) == 0;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.turret.turnTurret(DriveDirections.STOP);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
