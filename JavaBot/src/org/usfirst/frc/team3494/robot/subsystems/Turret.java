package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.turret.Shoot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	private Victor shooter_top = new Victor(RobotMap.shooterTop);
	private Victor shooter_bot = new Victor(RobotMap.shooterBottom);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
    /**
     * Run the shooter.
     * @param speed The speed to run the shooter at. This can be backwards, but...why?
     */
    public void shoot(double speed) {
    	shooter_top.set(speed);
    	shooter_bot.set(speed);
    }
}

