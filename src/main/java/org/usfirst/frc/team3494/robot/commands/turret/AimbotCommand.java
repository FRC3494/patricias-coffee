package org.usfirst.frc.team3494.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.Robot;
import org.usfirst.frc.team3494.robot.subsystems.Shooter;

/**
 * Command to automatically point the shooter towards a piece of retro-reflective
 * material (more exactly, to where ever {@link Robot#getCenterX()} returns.)
 * This command will <em>not</em> stop the shooter motors once the command stops
 * running.
 *
 * @see Shooter
 * @since 0.0.0
 */
public class AimbotCommand extends Command {

    public AimbotCommand() {
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
        double centerX = Robot.shooter.getCenterX();
        System.out.println("centerX: " + centerX);
        double centerDist = centerX - (Robot.getImgWidth() / 2);
        /*
         * if (centerX > 75 || centerX < -75) { if (centerDist < 0) {
         * Robot.turretRing.turnTurret(DriveDirections.LEFT); } else if
         * (centerDist > 0) {
         * Robot.turretRing.turnTurret(DriveDirections.RIGHT); } } else
         */
        double turnpower = Math.abs(centerDist * 0.006);
        if (turnpower > 0.15) {
            if (centerDist < 0) {
                Robot.shooter.preciseTurret(turnpower, DriveDirections.LEFT);
            } else {
                Robot.shooter.preciseTurret(turnpower, DriveDirections.RIGHT);
            }
        } else {
            Robot.shooter.turnTurret(DriveDirections.STOP);
        }
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
