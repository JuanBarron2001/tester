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

public class awesomeAuto extends Command {

  Timer a;
  AutoShoot b;
  SnapAndLock c;
  boolean finished;

  public awesomeAuto() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.limelight);
    requires(Robot.gyro);
    requires(Robot.pistons);
    requires(Robot.triangle);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.gyro.zeroOut();
    a = new Timer();
    setTimeout(300);
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.drivetrain.driveVoltage(-.25, 0);         //drive off
    a.delay(2);

    turnAngle(157);

    Robot.drivetrain.driveVoltage(0, 0);

    snapAndLock();

    AutoShoot();

    Robot.drivetrain.driveVoltage(-.25, 0);         //drive back
    a.delay(1);

    turnAngle(45);

    snapAndLock();

    Robot.triangle.upT();                         //get hatch

    Robot.drivetrain.driveVoltage(-.25, 0);       //drive back
    a.delay(1);

    turnAngle(180);

    snapAndLock();

    AutoShoot();

    finished = true;
  }

  private void turnAngle(double angle)
  {
    //if(Robot.gyro.getX() < angle)
    //{
      while(Robot.gyro.getX() < angle)
      {                  
        Robot.drivetrain.driveVoltage(0, -.5);
      }
    //}
    //else
    //{
      //while(Robot.gyro.getX() < angle)
      //{                  
        //Robot.drivetrain.driveVoltage(0, -.125);
      //}
    
    
  }

  private void snapAndLock()
  {
    Robot.limelight.update();                 //it updates the values from the limelight
    while(Robot.limelight.getArea() < 10)
    {
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
    if(Robot.drivetrain.getDistance() < 3)
    {
      Robot.drivetrain.driveVoltage(0, 0);
      break;
    }
  }
  }

  private void AutoShoot()
  {
    Timer b = new Timer();  //creates a timer for actions to be sequenced

    Robot.triangle.downT(); //triangle goes down
    b.delay(.75);           //delay for .75 secs
    Robot.pistons.push();   //pistons push out the hatch
    b.delay(.55);            //delay for .5 secs
    Robot.pistons.off();    //pistons retract

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

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
