/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Drivetrain.Drive;

import frc.robot.Mapping.RobotContainer;
import frc.robot.Mapping.SpeedControllerSetUp;
import frc.robot.Shooter.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  //If mode = 1, Arcade
  //If mode = 2, Tank
  public static int mode = 1;


  //Defining Subsystems

  private RobotContainer m_robotContainer;
  public static SpeedControllerSetUp speedcontrollersetup;
  AHRS ahrs;

  //Commands to be used later
  private Command m_autonomousCommand;
  public static boolean autoVal;
  public static String gameData;
  //private Command autonomousCommand;
  // private SendableChooser<Character> autoSide;
  // private SendableChooser<Character> autoGroup;
//  private SendableChooser<Command> chooser = new SendableChooser<>();


 
  //Initalizing
  public void robotInit() {

      //Sets up the camera
     // CameraServer.getInstance().startAutomaticCapture();
      //For limelight, use 10.31.0.11:5801

      //Gets what type of game is being played, not that important
      gameData = DriverStation.getInstance().getGameSpecificMessage();

      try {
        /***********************************************************************
         * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C) and USB. - See
         * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
         * 
         * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and USB. - See
         * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
         * 
         * VMX-pi: - Communication via USB. - See
         * https://vmx-pi.kauailabs.com/installation/roborio-installation/
         * 
         * Multiple navX-model devices on a single robot are supported.
         ************************************************************************/
        ahrs = new AHRS(SPI.Port.kMXP);
      } catch (RuntimeException ex) {
        DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
      }

      // ALWAYS INIT OI LAST

     // chooser.setDefaultOption("Auto Master", new AutonomousMaster(autoGroup.getSelected(), gameData, autoSide.getSelected()));

   //   autonomousCommand = new AutonomousMaster(autoGroup.getSelected(), gameData, autoSide.getSelected());

      //Autonomous Master Class


      //Options in the Smartdashboard
      // autoGroup = new SendableChooser<>();
      // autoGroup.addDefault("Group 1", '1');
      // autoGroup.addObject("Group 2", '2');
      // autoGroup.addObject("Group 3", '3');
      // autoGroup.addObject("Group 4", '4');
      // autoGroup.addObject("Test 5", '5');
      // SmartDashboard.putData("Autonomous", autoGroup);

      // autoSide = new SendableChooser<>();
      // autoSide.addObject("Left Side", 'L');
      // autoSide.addDefault("Right Side", 'R');
      // SmartDashboard.putData("Side", autoSide);


     // SmartDashboard.putData(drive);
     m_robotContainer = new RobotContainer();
      new SpeedControllerSetUp().configure();


  }
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
