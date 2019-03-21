/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class gyro extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  PigeonIMU pig;
  double [] ypr;                              //creates an array of double size 3

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public gyro()
  {
    pig = new PigeonIMU(Robot.box.gyro());                              //creates an array of double size 3
    ypr = new double[3];                              //creates an array of double size 3
    pig.getYawPitchRoll(ypr);  
  }  

  private void getYPR()
  {
    ypr = new double[3];                              //creates an array of double size 3
    pig.getYawPitchRoll(ypr);  
  }

  public double getX()
  {
    getYPR();
    System.out.println(ypr[0]);
    SmartDashboard.putNumber("X", ypr[0]);
    return ypr[0];
  }

  public double getY()
  {
    getYPR();
    return ypr[1];
  }

  public double getZ()
  {
    getYPR();
    return ypr[2];
  }

  public void zeroOut()
  {
    pig.setYaw(0);
    pig.setFusedHeading(0);
  }


}
