package org.usfirst.frc.team3494.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;

/**
 *
 */
public class TurnTurret extends Command {

    private DriveDirections dir;

    public TurnTurret(DriveDirections D) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("TurnTurret");
        requires(Robot.shooter);
        this.dir = D;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.shooter.turnTurret(this.dir);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !Robot.oi.getXbox_b().get() && !Robot.oi.getXbox_x().get();
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
