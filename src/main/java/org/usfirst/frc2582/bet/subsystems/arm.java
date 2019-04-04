/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
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

  private int targetPosition = 0; //postion arm is currently targeting
  private double horizontalHoldOutput = .25;//amount of power needed to hold arm horizontally

  //**********************************/
  //NEED TO FIGURE OUT THESE ENCODER VALUES
  //**********************************/
  public static final int ARM_FORWARD_LIMIT = 4900;
  public static final int ARM_REVERSE_LIMIT = -10;
  public static final int UP_POSITION = 0;
  public static final int DOWN_POSITION = 4800;
  public static final int ROCKET_POSITION =3300;
  public static final int DEPOT_POSITION = 4700;

  public static final int ARM_0_DEG =3700;
  public static final int ARM_90_DEG = 1400;


  /**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 30;

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
      arm.set(ControlMode.PercentOutput, y * 0.6);
      //double velocity = this.arm.getSelectedSensorVelocity(0);
      //SmartDashboard.putNumber("velocity", velocity);
  }

  public void motionMagicArm()
  {
  //arm.set(ControlMode.MotionMagic, targetPosition); //uses motion magic of the arm
  arm.set(ControlMode.MotionMagic,targetPosition, DemandType.ArbitraryFeedForward, getFeedForward() );
  
  }

  public int getTargetPosition()
  {
  return targetPosition;                              //it returns the ideal position to reach
  }

  public void setTargetPosition(int position)
  {
  targetPosition = position;                          //it sets the position to reach
  }

  public int getArmPosition() 
  {
  return arm.getSelectedSensorPosition(0);          //it returns the encoder value for the arm
  }

  public double getAngle()
  {
  int encoderCounts90Deg = ARM_90_DEG-ARM_0_DEG;
  double angleDegrees = (getArmPosition()-ARM_0_DEG)*90/encoderCounts90Deg;

  return angleDegrees;
  }

  public void zeroSensor()
  {
  arm.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);    //it sets the foot postion to 0
  }

  public void setPercentOut()
  {
  arm.set(ControlMode.PercentOutput,0);              //it sets the foot speed to 0;
  }

  public double getFeedForward() {

    // get the radians of the arm
    // getAngle() returns degrees
    double theta = Math.toRadians(getAngle());
  
    //SmartDashboard.putNumber("Angle", getAngle());
  
    // get a range of 0 to 1 to multiply by feedforward.
    // when in horizontal position, value should be 1
    // when in vertical up or down position, value should be 0 
    double gravityCompensation = Math.cos(theta);
  
    //SmartDashboard.putNumber("Gravity Compensation", gravityCompensation);
  
    // horizontalHoldOutput is the minimum power required to hold the arm up when horizontal
    // this is a range of 0-1, in our case it was .125 throttle required to keep the arm up
    double feedForward = -1*gravityCompensation * horizontalHoldOutput;
  
    //SmartDashboard.putNumber("Feed Forward", feedForward);
  
   return feedForward;
  
  }
  public boolean isUp()
    {
        Boolean hit = arm.getSensorCollection().isRevLimitSwitchClosed();     //it sets the value of hit if closes the limit switch
                                                 //it sets the blinkin to the value of hit
        return hit;                                                             //it returns the value of hit
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


    //Sets up the encoder to be absolute version of the ctre mag encoder
    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

    //Sets encoder phase this should be where the encoder counter goes up when the talon lights are green and down when lights are red
    //switch the boolan if it is counting backwards.  Motion magic will go haywire if this is not correct    
    arm.setSensorPhase(false);
    arm.setInverted(false);

    //Configure Talon to clear sensor position on Reverse  Limit "1 clears it 0 does not"
    arm.configSetParameter(ParamEnum.eClearPosOnLimitR, 1, 0, 0, 10);


    /* Set relevant frame periods to be at least as fast as periodic rate */
    arm.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    arm.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

    /* set the peak and nominal outputs */
    arm.configNominalOutputForward(0, kTimeoutMs);
    arm.configNominalOutputReverse(0, kTimeoutMs);
    arm.configPeakOutputForward(0.8, kTimeoutMs);
    arm.configPeakOutputReverse(-0.8, kTimeoutMs);

    /* set closed loop gains in slot0 - see documentation */
    arm.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
    arm.config_kF(0, 0.0, kTimeoutMs);
    arm.config_kP(0, 4.0, kTimeoutMs);
    arm.config_kI(0, 0, kTimeoutMs);
    arm.config_kD(0, 0, kTimeoutMs);

    /* set acceleration and vcruise velocity - see documentation */
    arm.configMotionCruiseVelocity(400, kTimeoutMs);
    arm.configMotionAcceleration(200, kTimeoutMs);

    /* zero the sensor */
   // for an absolute sensor this will not actually be zero, but the current location
   arm.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);



    //Use these settings to turn on/off software limits and set the limits
    arm.configForwardSoftLimitEnable(false);
    arm.configForwardSoftLimitThreshold(ARM_FORWARD_LIMIT);

    arm.configReverseSoftLimitEnable(false);
    arm.configReverseSoftLimitThreshold(ARM_REVERSE_LIMIT);



    arm.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                                    LimitSwitchNormal.NormallyOpen,
                                    kTimeoutMs);

    
  }


}
