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
public class blinkin extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark blinkin;            //it initialize a spark
  private double color;             //it inititalize a double for color arrangements

  public blinkin(Alliance a)        //it is a constructor for blinkin and it needs an alliance
  {
    blinkin = new Spark(0);         //it creates a spark in pwm 0
    
    if(a.equals(Alliance.Blue))     //it checks if alliance is blue
    {
      color = .87;                  //it sets color to .87(blue)
    }
    else if(a.equals(Alliance.Red)) //it checks if alliance is red
    {
      color = .61;                  //it sets color to .61(red)
    }
    else
    {
      color = .99;                  //it sets color to .99(black) if it doesnt match an alliance
    }
    
    blinkin.set(.91);             //it sets blikin to color
  }

  

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void limitSwitch(boolean b)  //it takes a boolean to check if limitstich is pressed
  {
    if(b)
      {
        blinkin.set(.51);             //it sets blinkin to .15(strobe color 1)
      }
      else
      {
        blinkin.set(color);           //it sets to alliance color
      }
    }

    public void box(boolean c)
    {
      {
        if(!c)
        {
         blinkin.set(.35);             //it sets blinkin to .35(strobe color 2)
        }
        else
        {
          blinkin.set(color);         //it sets to alliance color
        }
      }
    }

}
