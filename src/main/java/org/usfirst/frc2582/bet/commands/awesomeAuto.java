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
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.gyro.zeroOut();
    Timer a = new Timer();
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.drivetrain.driveVoltage(-.25, 0);         //drive off
    a.delay(2);

    turnAngle(180);

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
    while(Robot.gyro.getX()%360 < angle)
    {                  
      Robot.drivetrain.driveVoltage(0, .125);
    }
  }

  private void snapAndLock()
  {
    c.initialize();                                 //snap and lock
    while(Robot.limelight.getArea() < 20)
    {
      c.execute();
    }
    c.end();
  }

  private void AutoShoot()
  {
    b.initialize();                                 //auto shoot
    b.execute();
    b.end();
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
