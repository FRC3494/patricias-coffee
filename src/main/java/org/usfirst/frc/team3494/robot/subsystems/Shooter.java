package org.usfirst.frc.team3494.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.turret.Shoot;

/**
 * Shooter subsystem. Contains all methods for controlling the robot's shooter.
 *
 * @since 0.0.0
 */
public class Shooter extends Subsystem {
    private Talon shooter_top = new Talon(RobotMap.shooterTop);
    private Talon shooter_bot = new Talon(RobotMap.shooterBottom);

    public Shooter() {
        super("Shooter");
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Shoot());
    }

    /**
     * Run the shooter.
     *
     * @param speed The speed to run the shooter at. This can be backwards,
     *              but...why?
     */
    public void shoot(double speed) {
        shooter_top.set(speed);
        shooter_bot.set(speed);
    }
}
