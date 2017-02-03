package org.usfirst.frc.team3494.robot;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3494.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3494.robot.subsystems.Lifter;
import org.usfirst.frc.team3494.robot.subsystems.MemSys;
import org.usfirst.frc.team3494.robot.subsystems.Turret;
import org.usfirst.frc.team3494.robot.vision.GripPipeline;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.vision.VisionThread;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain driveTrain;
	public static Lifter lifter;
	public static Turret turret;
	public static MemSys memSys;
	public static OI oi;

	// vision
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static RobotDrive wpiDrive;

	VisionThread visionThread;
	private double centerX = 0.0;
	private ArrayList<MatOfPoint> filteredContours;

	private final Object imgLock = new Object();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// init subsystems
		driveTrain = new Drivetrain();
		lifter = new Lifter();
		turret = new Turret();
		memSys = new MemSys();
		oi = new OI();
		// start vision thread
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(getImgWidth(), IMG_HEIGHT);
		camera.setExposureManual(15);
		camera.setWhiteBalanceManual(VideoCamera.WhiteBalance.kFixedIndoor);
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				MatOfPoint firstCont = pipeline.filterContoursOutput().get(0);
				MatOfPoint secondCont = pipeline.filterContoursOutput().get(1);
				double average_y_one = 0;
				for (Point p : firstCont.toList()) {
					average_y_one += p.y;
				}
				double average_y_two = 0;
				for (Point p : secondCont.toList()) {
					average_y_two += p.y;
				}
				// divide by number of points to give actual average
				average_y_two = average_y_two / secondCont.toList().size();
				average_y_one = average_y_one / firstCont.toList().size();
				Rect r = Imgproc.boundingRect(pipeline.findContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2);
					filteredContours = pipeline.filterContoursOutput();
				}
			}
		});
		visionThread.start();
		wpiDrive = driveTrain.wpiDrive;
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings &amp; commands.
	 */
	@Override
	public void autonomousInit() {
		try {
			autonomousCommand = chooser.getSelected();
		} catch (NullPointerException e) {
			System.out.println("Caught NPE in auto init. Is there a button chooser on the SmartDashboard?");
			autonomousCommand = null;
		}

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// commented out for vision
		// Scheduler.getInstance().run();
		double centerX;
		// Rect rect;
		synchronized (imgLock) {
			centerX = this.centerX;
			// rect = this.rect;
		}
		double turn = centerX - (getImgWidth() / 2);
		// drive with turn
		wpiDrive.arcadeDrive(0.5, (turn * 0.005) * -1);
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		memSys.setCenterX(centerX);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public double getCenterX() {
		return centerX;
	}

	public static int getImgWidth() {
		return IMG_WIDTH;
	}

	public ArrayList<MatOfPoint> getFilteredContours() {
		return filteredContours;
	}

	public void setFilteredContours(ArrayList<MatOfPoint> filteredContours) {
		this.filteredContours = filteredContours;
	}
}
