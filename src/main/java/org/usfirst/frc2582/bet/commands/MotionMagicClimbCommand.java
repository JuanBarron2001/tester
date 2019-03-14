/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MotionMagicClimbCommand extends Command 
{
  public MotionMagicClimbCommand() 
  {
    requires(Robot.foot);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.foot.setTargetPosition(-5800);  //this sets the desired amount of clicks the encoder needs to reach
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.foot.motionMagicJump();         //it executes the foots motion magic
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()          
  {
    return (Math.abs(Robot.oi.gamepad.getY())>0.1); //returns true when the gampad joystick is greater than .1 either up or down
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.foot.setPercentOut();           //sets the foot to percentOut(it turns off the foot)
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();                                //it calls the end method
  }
}
