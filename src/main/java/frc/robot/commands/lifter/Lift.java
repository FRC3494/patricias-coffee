package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lifter;

/**
 * Lifter subsystem default command
 *
 * @see Lifter
 */
public class Lift extends Command {

    public Lift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double leftTrigger = Robot.oi.xbox.getTriggerAxis(Hand.kLeft);
        if (!(leftTrigger > 0) && !(Robot.oi.getXbox_lb().get())) {
            Robot.lifter.stopLift();
        } else if (Robot.oi.getXbox_lb().get()) {
            Robot.lifter.lift(false);
        } else if (leftTrigger > 0) {
            Robot.lifter.lift(true);
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
