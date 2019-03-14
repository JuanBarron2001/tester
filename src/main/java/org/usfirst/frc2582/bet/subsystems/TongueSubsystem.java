/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import org.usfirst.frc2582.bet.commands.TongueMoveCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class TongueSubsystem extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid toungueSolenoid;

  public TongueSubsystem()
  {
    toungueSolenoid  = new DoubleSolenoid(0, 2, 1);         //it declares the tongueSolenoid in PCM port 0, forward channel 2, backward channel 1
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TongueMoveCommand());
  }

  public void tongueOut()
   {
      toungueSolenoid.set(Value.kReverse);                  //it sets the tongue to out using the reverse channel
   }
  public void tongueIn()
  {
      toungueSolenoid.set(Value.kForward);                  //it sets the tongue to go in using the forward channel
  }

  public void tongueOff()
  {
    toungueSolenoid.set(Value.kOff);                        //it turns the tongue off
  }


}

