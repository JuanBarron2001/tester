/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ultraAssist extends Command {
  public ultraAssist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double dist = Robot.drivetrain.getDistance();         // This gets the distance from the ultrasonic
    if ( dist < 5 )                                       // and returns it in inches.
    {
      double x = Robot.oi.getjoy().getX();
      double y = Robot.oi.getjoy().getY();
      double z = Robot.oi.getjoy().getZ();

      double chan = (z + x)/3;                            // Combines the x and z axis, slowing the turning
  
      Robot.drivetrain.driveVoltage(-y, chan);            // This sets the 'slow speed' for turning
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
