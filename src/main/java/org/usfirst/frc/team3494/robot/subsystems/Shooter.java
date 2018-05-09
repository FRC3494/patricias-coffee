package org.usfirst.frc.team3494.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.UnitTypes;
import org.usfirst.frc.team3494.robot.commands.turret.Shoot;

/**
 * Shooter subsystem. Contains all methods for controlling the robot's shooter.
 *
 * @since 0.0.0
 */
public class Shooter extends Subsystem {
    private Talon shooter_top = new Talon(RobotMap.shooterTop);
    private Talon shooter_bot = new Talon(RobotMap.shooterBottom);

    private Spark turret_con = new Spark(RobotMap.turretMotor);
    private Encoder turret_enc;

    private double centerX = 0.0;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Shooter() {
        super("Shooter");
        shooter_top.setInverted(true);
        shooter_bot.setInverted(true);

        turret_enc = new Encoder(RobotMap.turretEncoder_A, RobotMap.turretEncoder_B);
        turret_enc.setDistancePerPulse(1 / 420);
        turret_enc.reset();
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

    /**
     * Turn the shooter.
     *
     * @param dir The direction to turn in. Only use
     *            {@link DriveDirections#LEFT} or {@link DriveDirections#RIGHT}.
     *            Any other value will stop the shooter.
     */
    public void turnTurret(DriveDirections dir) {
        if (dir.equals(DriveDirections.LEFT)) {
            turret_con.set(-0.5);
        } else if (dir.equals(DriveDirections.RIGHT)) {
            turret_con.set(0.5);
        } else {
            turret_con.stopMotor();
        }
    }

    /**
     * Turns the shooter at a given speed.
     *
     * @param power The power to turn the shooter at. This should be
     *              <em>positive</em>, regardless of the direction to turn in.
     * @param dir   The direction to turn in. This should be either
     *              {@link DriveDirections#LEFT} or {@link DriveDirections#RIGHT}.
     *              Anything else will stop the shooter.
     */
    public void preciseTurret(double power, DriveDirections dir) {
        if (dir.equals(DriveDirections.LEFT)) {
            turret_con.set(-power);
        } else if (dir.equals(DriveDirections.RIGHT)) {
            turret_con.set(power);
        } else {
            turret_con.stopMotor();
        }
    }

    public Encoder getTurret_enc() {
        return turret_enc;
    }

    public void resetTurret_enc() {
        turret_enc.reset();
    }

    /**
     * Gets the distance from zero of the tiny little gear on the output shaft.
     *
     * @param get The unit the returned data should be in. Doesn't take pixels,
     *            because...why?
     * @return The distance from the encoder's zero position, as a double.
     * @see org.usfirst.frc.team3494.robot.UnitTypes
     */
    public double getTurretEncDistance(UnitTypes get) {
        if (get.equals(UnitTypes.RAWCOUNT)) {
            return turret_enc.get();
        } else if (get.equals(UnitTypes.MILLIMETERS)) {
            // distance = wheel circumference * wheel rotations = (pi * d) * # of
            // counts/N * 360, assuming encoder on output shaft
            // if there is a gearbox in the way, distance =
            double pi = Math.PI;
            int N = 420; // Pulses per revolution :flag_fr:
            double d = 18.34; // diameter of gear on output shaft
            return ((pi * d) * (turret_enc.get() / N) * 360);
        } else {
            // i don't know how you got here but you should probably leave
            return 0;
        }
    }

    public double getCenterX() {
        return this.centerX;
    }

    public void setCenterX(double x) {
        this.centerX = x;
    }
}
