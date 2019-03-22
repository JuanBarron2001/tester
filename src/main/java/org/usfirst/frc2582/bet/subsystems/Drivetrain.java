// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2582.bet.subsystems;


import org.usfirst.frc2582.bet.Robot;
import org.usfirst.frc2582.bet.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivetrain extends Subsystem 
{

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private TalonSRX leftFront;
    private VictorSPX leftBack;
    
    private TalonSRX rightFront;
    private VictorSPX rightBack;
    
    private Ultrasonic ult1;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Drivetrain() 
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        leftFront = new TalonSRX(4);  //master 
        leftBack = new VictorSPX(1); //slave
        
        leftFront.configFactoryDefault();
        leftBack.configFactoryDefault();
        
        leftBack.follow(leftFront);  //assinging a slave to master

        rightFront = new TalonSRX(6); //master
        rightBack = new VictorSPX(3);  //slave

        rightFront.configFactoryDefault();
        rightBack.configFactoryDefault();
        
        rightBack.follow(rightFront);  //assigning a slave to master

        ult1 = new Ultrasonic(8,9);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() 
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new joydrive());      //joydrive is the main command that requires drivetrain?
    }

    @Override
    public void periodic() 
    {
        // Put code here to be run every loop

    }

    //just call the master and set the values to the thing and it should work
    //this method uses parameters of x and y to set the value of wheels
    // using controlmode percent output
    public void driveVoltage(double x, double y)
    {
        double speed = x + y;           //sets the speed of the left wheels
        double speed1 = x - y;          //sets the speed of the right wheels

        leftFront.set(ControlMode.PercentOutput, speed);        //sets the LeftMaster to speed
        
        rightFront.set(ControlMode.PercentOutput, -speed1);     //sets the RightMaster to speed1
        
        getDistance();
    }

    public double getDistance()
    {
        double dis = ult1.getRangeInches();
        SmartDashboard.putNumber("Inch:", dis);
        return dis;
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

