/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.commands;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import sun.tools.tree.WhileStatement;

public class AutoShoot extends Command 
{
  boolean done;  //variable to say if the action is done

  public AutoShoot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.triangle);
    requires(Robot.pistons);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    done = false;  //sets it to false

    Timer a = new Timer();  //creates a timer for actions to be sequenced

    Robot.triangle.downT(); //triangle goes down
    a.delay(.75);           //delay for .75 secs
    Robot.pistons.push();   //pistons push out the hatch
    a.delay(.5);            //delay for .5 secs
    Robot.pistons.off();    //pistons retract

    done = true;            //sets it to true
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return done;              //sets it to true
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.pistons.off();     //pistons retract
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    done = true;              //sets it to true
  }
}
