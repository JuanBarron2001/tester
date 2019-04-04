/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc2582.bet.commands.gameArm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class arm extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX arm;
  private DigitalInput limit;

  public arm()
  {
    arm = new TalonSRX(61);
    //limit = new DigitalInput(1);
    arm.configFactoryDefault();
    //arm.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    //arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  public void armMove(double y)
  {
      //stop();
      arm.set(ControlMode.PercentOutput, y * .5);
      //double velocity = this.arm.getSelectedSensorVelocity(0);
      //SmartDashboard.putNumber("velocity", velocity);
  }

  /*public void armSet(int pos)
  {
    //stop();
    arm.setSelectedSensorPosition(pos);
    SmartDashboard.putNumber("armPost", pos);
  }*/

  /*public void resetLimit(boolean b)
  {
    if(b)
    {
      arm.setSelectedSensorPosition(0);
    }
  }*/

  /*public void stop()
  {
    if(arm.getSelectedSensorPosition() > 2000 || !limit.get())
    {
      arm.set(ControlMode.PercentOutput, 0);
    }
  }*/

  /*public int getPos()
  {
    return arm.getSelectedSensorPosition();
  }*/

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new gameArm());
  }


}
