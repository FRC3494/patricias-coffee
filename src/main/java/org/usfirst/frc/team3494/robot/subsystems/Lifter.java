package org.usfirst.frc.team3494.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.lifter.Lift;

/**
 * Lift subsystem. Contains all methods for controlling the robot's ball lifter.
 *
 * @since 0.0.0
 */
public class Lifter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Talon lift_top_back = new Talon(RobotMap.liftTopBack);
    private Talon lift_top_front = new Talon(RobotMap.liftTopFront);
    private Talon lift_bottom = new Talon(RobotMap.liftBottom);

    public Lifter() {
        super("Lifter");
        lift_bottom.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Lift());
    }

    /**
     * Runs the lift.
     *
     * @param up Whether the lift should go up. {@code true} for up,
     *           {@code false} for down.
     */
    // TODO: Change this from booleans to a proper enum or something.
    public void lift(boolean up) {
        if (up) {
            lift_bottom.set(0.5);
            lift_top_front.set(0.5);
            lift_top_back.set(0.5);
        } else {
            lift_bottom.set(-0.5);
            lift_top_front.set(-0.5);
            lift_top_back.set(-0.5);
        }
    }

    /**
     * Stops the lift.
     */
    public void stopLift() {
        lift_bottom.stopMotor();
        lift_top_front.stopMotor();
        lift_top_back.stopMotor();
    }
}
