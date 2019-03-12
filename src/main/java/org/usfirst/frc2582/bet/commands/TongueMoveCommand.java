/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2582.bet.Robot;


public class TongueMoveCommand extends Command {
  public TongueMoveCommand() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.tongueSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double value = Robot.oi.gamepad.getRawAxis(5);//gets Y value of right joystick
    //System.out.println(value);
    if (value < -0.3){
      Robot.tongueSubsystem.tongueOut();
      //System.out.println("TOut");
    }
    else if (value > 0.3){
      Robot.tongueSubsystem.tongueIn();
      //System.out.println("TIn");
    }
    else{
      Robot.tongueSubsystem.tongueOff();
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.tongueSubsystem.tongueOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
