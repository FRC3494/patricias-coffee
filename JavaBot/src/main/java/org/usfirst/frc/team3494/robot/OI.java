package org.usfirst.frc.team3494.robot;

import org.usfirst.frc.team3494.robot.commands.turret.AimbotCommand;
import org.usfirst.frc.team3494.robot.commands.turret.GoHome;
import org.usfirst.frc.team3494.robot.commands.turret.ResetTurretEnc;
import org.usfirst.frc.team3494.robot.commands.turret.StopTurret;
import org.usfirst.frc.team3494.robot.commands.turret.TurnTurret;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	public final Joystick flight_one = new Joystick(0);
	public final Joystick flight_two = new Joystick(1);
	public final XboxController xbox = new XboxController(2);
	private final JoystickButton xbox_a = new JoystickButton(xbox, 1);
	private final JoystickButton xbox_b = new JoystickButton(xbox, 2);
	private final JoystickButton xbox_x = new JoystickButton(xbox, 3);
	private final JoystickButton xbox_y = new JoystickButton(xbox, 4);
	private final JoystickButton xbox_lb = new JoystickButton(xbox, 5);
	private final JoystickButton xbox_start = new JoystickButton(xbox, 7);
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public OI() {
		xbox_start.whenPressed(new ResetTurretEnc());
		xbox_a.whileHeld(new AimbotCommand());
		xbox_a.whenReleased(new StopTurret());
		xbox_x.whenPressed(new TurnTurret(DriveDirections.LEFT));
		xbox_b.whenPressed(new TurnTurret(DriveDirections.RIGHT));
		xbox_y.whenPressed(new GoHome());
	}

	public JoystickButton getXbox_a() {
		return xbox_a;
	}

	public JoystickButton getXbox_b() {
		return xbox_b;
	}

	public JoystickButton getXbox_lb() {
		return xbox_lb;
	}

	public JoystickButton getXbox_x() {
		return xbox_x;
	}
}
