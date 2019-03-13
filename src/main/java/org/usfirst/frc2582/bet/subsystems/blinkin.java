/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2582.bet.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class blinkin extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark blinkin;
  private double color;

  public blinkin(Alliance a)
  {
    blinkin = new Spark(0);

    if(a.equals(Alliance.Blue))
    {
      color = .87;
    }
    else if(a.equals(Alliance.Red))
    {
      color = .61;
    }
    else
    {
      color = .99;
    }

    blinkin.set(color);
  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void used(boolean green)
    {
        if(!green)
        {
            blinkin.set(.77);
        }
        else
        {
            blinkin.set(color);
        }
    }

}
