/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * @author Regina Alexander
 *  
 * @version 1.2
 */

package org.usfirst.frc2582.bet.commands;

import java.text.BreakIterator;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc2582.bet.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoBox extends Command 
{

  private Boolean finished;  //variable to say if its finished  
  private Boolean spit;      //variable to say which action to preform


  public AutoBox() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.box);
    requires(Robot.tongueSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    finished = false;  //this will tell it when it is done
    spit = Robot.box.IsBallThere();  //this sets the condition of the roller
  }
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.box.IsBallThere() != spit)  //when spit doesnt match with condition then it has 'tripped'
    {                                    //the sensor
      finished = true;
    }

    if(!spit) //this might need to move to periodic
    {
      Robot.tongueSubsystem.tongueOut();
    }
    
    if(spit)
    {
      Robot.box.spit();                  //spit
    }
    else
    {
      Robot.box.suck();                  //suck
    }
  }
      
    
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Timer b = new Timer();
    b.delay(.1);  //time added so it can secure the ball
    Robot.box.stop();
    //Robot.tongueSubsystem.tongueIn();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {   
    finished = true;  //its going to call finished when its interrupted
  }
}

