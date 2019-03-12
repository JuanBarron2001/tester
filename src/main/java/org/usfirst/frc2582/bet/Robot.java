// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
// this is so cool


package org.usfirst.frc2582.bet;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import sun.nio.ch.Net;

import org.usfirst.frc2582.bet.commands.*;
import org.usfirst.frc2582.bet.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static foot foot;
    public static box box;
    public static triangle triangle;
    public static pistons pistons;
    public static limelight limelight;
    public static TongueSubsystem tongueSubsystem = new TongueSubsystem();
    
    public static DriverStation ds = DriverStation.getInstance();  //this is to get value from driver station
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        foot = new foot();
        box = new box();
        triangle = new triangle();
        pistons = new pistons();
        limelight = new limelight();  //this is camera
        
        CameraServer.getInstance().startAutomaticCapture();
        SmartDashboard.putNumber("Arm Position", Robot.foot.getFootPosition());


        //i making a comment

        DriverStation.Alliance a = Alliance.Blue;  //enum
        if(ds.getAlliance() == a)  //use to set led light
        {

        }
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
    }

    @Override
    public void robotPeriodic() {
    SmartDashboard.putNumber("Arm Position", Robot.foot.getFootPosition());
    SmartDashboard.putBoolean("Foot Limit", Robot.foot.isReverseLimit());
    
    }
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     * Project COC
     * Code
     * On
     * Crack
     */
    @Override
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();

        /*Timer a = new Timer();
        a.reset();
        a.start();
        while(a.get() < 4)
        {
            Robot.limelight.update();

            if(Robot.limelight.isThereTarget())
            {
                double x = Math.cbrt(Robot.limelight.driveSet());
                double y = Math.cbrt(Robot.limelight.steer());
                Robot.drivetrain.driveVoltage(x, y);
            }
            else
            {
                Robot.drivetrain.driveVoltage(0, 0);
            }
        }

        Robot.triangle.upT();
        a.delay(.75);
        Robot.pistons.push();
        a.delay(.5);
        Robot.pistons.off();
        Robot.triangle.downT();

        a.reset();
        a.start();
        while(a.get() < 1)
        {
            Robot.drivetrain.driveVoltage(-.5, .25);
        }

        while(a.get() < 4)
        {
            Robot.limelight.update();

            if(Robot.limelight.isThereTarget())
            {
                double x = Math.cbrt(Robot.limelight.driveSet());
                double y = Math.cbrt(Robot.limelight.steer());
                Robot.drivetrain.driveVoltage(x, y);
            }
            else
            {
                Robot.drivetrain.driveVoltage(0, 0);
            }
        }
        */
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.triangle.upT();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.box.led(Robot.box.IsBallThere());
        //CameraServer.getInstance().startAutomaticCapture();
        //Robot.box.printLOL();
    }
}
