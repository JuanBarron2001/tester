/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SnapAndLock extends Command 
{
  public SnapAndLock() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.limelight.update();                 //it updates the values from the limelight

    if(Robot.limelight.isThereTarget())       //it checks if the limelight sees a target
    {
      double x = Robot.limelight.driveSet();  //sets the value for driving
      double y = Robot.limelight.steer();     //sets the value for steering

      Robot.drivetrain.driveVoltage(x, y);    //drivevoltage(differential drive) gets the drive and steer
    }
    else
    {
      Robot.drivetrain.driveVoltage(0, 0);    //it stops driving
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
