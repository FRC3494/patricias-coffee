package org.usfirst.frc.team3494.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;

/**
 * if you're reading this doc because you don't know what this class does you're
 * significantly screwed
 */
public class StopTurret extends Command {

    public StopTurret() {
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
        Robot.turret.turnTurret(DriveDirections.STOP);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
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
