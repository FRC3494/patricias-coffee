package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveDirections;
import frc.robot.Robot;
import frc.robot.UnitTypes;
import frc.robot.subsystems.Turret;

/**
 * Command to go to the encoder 0 position. Stops the shooter once it's there.
 *
 * @see Turret
 * @see Turret#getTurretEncDistance
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
        end();
    }
}
