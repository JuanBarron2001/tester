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

public class AutoBox extends Command {


  private Boolean finished;  
  private Boolean spit;


  public AutoBox() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.box);
      
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    finished = false;  //this will tell it when it is done
    spit = Robot.box.IsBallThere();  //this sets the condition of the roller
    /*Timer b = new Timer();
    finished = false;
    if(Robot.box.IsBallThere())
    {
        Robot.box.spit();
        while(Robot.box.IsBallThere())
        {
         //b.delay(.01);  //delay may affect other commands lol
        }
    }
    else
    {
      Robot.box.suck();
      while(!Robot.box.IsBallThere())
      {
        //b.delay(.01);
      }
    }
    //Robot.box.stop();
    
    //Robot.box.close();
    finished = true;
  */}
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.box.IsBallThere() != spit)  //when spit doesnt match with condition then it has 'tripped'
    {                                    //the sensor
      finished = true;
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
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    //delay(3);
    Robot.box.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    //end();
    System.out.println("this is finished");
    //end();  this might still be the correct way
    finished = true;
  }

  private void delay(double d) {
  }
}
