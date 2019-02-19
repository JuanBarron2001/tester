// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2582.bet;

import org.usfirst.frc2582.bet.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc2582.bet.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick joy;
    public Joystick gamepad;

    public JoystickButton button1;
    public JoystickButton button2;
    public JoystickButton button3;
    public JoystickButton button4;
    public JoystickButton button5;
    public JoystickButton button6;
    public JoystickButton button7;
    public JoystickButton button8;




    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        gamepad = new Joystick(1);
        
        joy = new Joystick(0);
        
        button1 = new JoystickButton(gamepad, 1);
        button1.whenPressed(new up());
        
        button2 = new JoystickButton(gamepad, 2);
        button2.whenPressed(new down());

        button3 = new JoystickButton(gamepad, 3);
        button3.whileHeld(new push());

        button4 = new JoystickButton(gamepad, 4);
        button4.whileHeld(new intake());
    
        button5 = new JoystickButton(gamepad, 5);
        button5.whileHeld(new outtake());

        button6 = new JoystickButton(gamepad, 6);
        button6.whileHeld(new jump());

        button7 = new JoystickButton(gamepad, 7);
        button7.whenPressed(new AutoShoot());

        button8 = new JoystickButton(gamepad, 8);
        button8.whenPressed(new AutoBox());
        //button8.cancelWhenActive(new AutoBox());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("joydrive", new joydrive());
        SmartDashboard.putData("intake", new intake());
        SmartDashboard.putData("outtake", new outtake());
        SmartDashboard.putData("jump", new jump());
        SmartDashboard.putData("push", new push());
        SmartDashboard.putData("up", new up());
        SmartDashboard.putData("down", new down());
        SmartDashboard.putData("auto", new AutoBox());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getjoy() {
        return joy;
    }

    public Joystick getgamepad() {
        return gamepad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

