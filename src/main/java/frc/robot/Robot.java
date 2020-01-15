/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.OI;
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
  public static int mode = 2;


  //Defining Subsystems
  public static OI oi;
  public static Drive drive;
  private final Drive Drive = new frc.robot.Drivetrain.Drive();
  public static Shooter shooter;
  public static SpeedControllerSetUp speedcontrollersetup;
  AHRS ahrs;

  //Commands to be used later
  public static boolean autoVal;
  public static String gameData;
  //private Command autonomousCommand;
  // private SendableChooser<Character> autoSide;
  // private SendableChooser<Character> autoGroup;
//  private SendableChooser<Command> chooser = new SendableChooser<>();


 
  //Initalizing
  public void robotInit() {


      //Initalizes the drive subsystem
      drive = new Drive();
      shooter = new Shooter();


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
      oi = new OI();
      new SpeedControllerSetUp().configure();


  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
 
  public void disabledInit() {

  }


  public void disabledPeriodic() {
  
  }


 
  public void autonomousInit() {

   //   autonomousCommand = new AutonomousMaster(autoGroup.getSelected(), gameData, autoSide.getSelected());
   //   autonomousCommand.start();
      //Tells the autonomous command to run
      autoVal = true;

      // schedule the autonomous command (example)
      
  }

  /**
   * This function is called periodically during autonomous.
   */

  public void autonomousPeriodic() {

    //  Scheduler.getInstance().run();

  }

 
  public void teleopInit() {

      if(autoVal) {
    //      if(autonomousCommand.isRunning()) {
    //          autonomousCommand.cancel();
          }
      
    //  autoVal = false;
     // autonomousCommand.cancel();


      // This makes sure that the autonomous stops running when
      // teleop starts running. If you want the autonomous to
      // continue until interrupted by another command, remove
      // this line or comment it out.
    //  if (autonomousCommand != null) {
   //       autonomousCommand.cancel();
   //   }
   Scheduler.getInstance().run();
    }

  /**
   * This function is called periodically during operator control.
   */

  public void teleopPeriodic() {

      Scheduler.getInstance().run();
 }

  /**
   * This function is called periodically during test mode.
   */

  public void testPeriodic() {


  }
}