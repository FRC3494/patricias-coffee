package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveDirections;
import frc.robot.Robot;

public class TurnTurret extends Command {

    private DriveDirections dir;

    public TurnTurret(DriveDirections D) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("TurnTurret");
        requires(Robot.turret);
        this.dir = D;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.turret.turnTurret(this.dir);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !Robot.oi.getXbox_b().get() && !Robot.oi.getXbox_x().get();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.turret.turnTurret(DriveDirections.STOP);
    }
}
