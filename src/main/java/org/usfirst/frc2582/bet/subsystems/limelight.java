/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class limelight extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  final double STEER_K = 0.05;                    // how hard to turn toward the target
  final double DRIVE_K = 0.1;                   // how hard to drive fwd toward the target
  final double DESIRED_TARGET_AREA = 14;//13        // Area of the target when the robot reaches the wall
  final double MAX_DRIVE = 0.6;                   // Simple speed limit so we don't drive too fast

  double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

  private boolean ledOn;

  public limelight()
  {
    ledOn = true;
  }

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }

  public void update()      //this is to update the values of tv,tx,ty,ta
  {
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public boolean isThereTarget()              //this checks if there is a target
  {
    if (tv < 0)                             
      {
        return false;                         //if tv < 1.0 then it returns false
      }
    else
    {
        return true;                          //else it returns true
    }
  }

  public double steer()
  {
    return tx * STEER_K;                      //this returns how much steer dependent on kValue and the x - angle
  }

  public double drive()
  {
    return (DESIRED_TARGET_AREA - ta) * DRIVE_K; //this returns how much to drive dependent on how much area to cover and the kValue
  }
  
  public double driveSet()
  {
    if (drive() > MAX_DRIVE)                  //if drive() is greater than the limit
      {
          return MAX_DRIVE;                   //it returns the limit
      }
      return drive();                         //else it returns the drive()
  }

  public double getArea()
  {
    return ta;
  }

  public void ledOn()//this is to turn on led lights
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    ledOn = true;
  }

  public void ledOff()//this is to turn off led lights
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    ledOn = false;
  }

  public boolean getIsOn()
  {
    return ledOn;
  }
}
