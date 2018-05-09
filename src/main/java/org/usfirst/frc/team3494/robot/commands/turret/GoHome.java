package org.usfirst.frc.team3494.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;
import org.usfirst.frc.team3494.robot.UnitTypes;
import org.usfirst.frc.team3494.robot.subsystems.Shooter;

/**
 * Command to go to the encoder 0 position. Stops the shooter once it's there.
 *
 * @see Shooter
 * @see Shooter#getTurretEncDistance
 */
public class GoHome extends Command {

    public GoHome() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double location = Robot.shooter.getTurretEncDistance(UnitTypes.MILLIMETERS);
        if (location > 0) {
            Robot.shooter.turnTurret(DriveDirections.LEFT);
        } else {
            Robot.shooter.turnTurret(DriveDirections.RIGHT);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.shooter.getTurretEncDistance(UnitTypes.MILLIMETERS) == 0;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shooter.turnTurret(DriveDirections.STOP);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
