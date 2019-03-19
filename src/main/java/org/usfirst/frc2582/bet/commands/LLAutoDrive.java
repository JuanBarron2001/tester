/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LLAutoDrive extends Command 
{
  public LLAutoDrive() 
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
    Robot.limelight.ledOn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.limelight.update();                 //it updates the values from the limelight
    double move = -Robot.oi.joy.getY();
    double turn = Robot.oi.joy.getX();

    if(Robot.limelight.isThereTarget())       //it checks if the limelight sees a target
    {
      Robot.limelight.autoAlignLL(move, 0.5, 0); //Auto aligns using LL move uses joystick value to for throttle and LL to aim kF feed forward constant kP Proportional Constant

    }
    else
    {
      Robot.drivetrain.driveVoltage(turn , move);    //moves to driver input
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
    Robot.limelight.ledOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}
