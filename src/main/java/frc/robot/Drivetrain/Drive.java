/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mapping.RobotMap;

public class Drive extends Subsystem {


    private double limitSpeed = 0;
    private double limitRotate = 0;

    private double scaleSpeed;
    private double scaleRotate;

    private double moveAccelerationLimit = 0.05;
    private double rotateAccelerationLimit = 0.08; //Velocity - Tune for different drivetrain, if it's too low, sluggish



    public Drive() {
        super("Drive");
    }

    //Arcade Drive, one Joystick controls forwards/backwards, the other controls turning
    public void arcadeDrive(double moveSpeed, double rotateSpeed) {

        //Limits for speed, using quadratics and max/min
        moveSpeed = deadband(moveSpeed);
        rotateSpeed = deadband(rotateSpeed);

        scaleSpeed = moveSpeed < 0 ? -0.7 : 0.7;
        scaleRotate = rotateSpeed < 0 ? -0.6 : 0.6;

        moveSpeed *= scaleSpeed * moveSpeed;
        rotateSpeed *= scaleRotate * rotateSpeed;


        //Acceleration Curve, takes the difference of the input value and a limited value
        //Checks to see if it's greater than the limit for acceleration

        // M O V E
       if(moveSpeed - limitSpeed > moveAccelerationLimit){
           limitSpeed += moveAccelerationLimit;
       }else if (moveSpeed - limitSpeed < -moveAccelerationLimit){
           limitSpeed -= moveAccelerationLimit;
       }else if (moveSpeed - limitSpeed <= moveAccelerationLimit){
           limitSpeed = moveSpeed;
       }


       //R O T A T E
        if(rotateSpeed - limitRotate > rotateAccelerationLimit){
            limitRotate += rotateAccelerationLimit;
        }else if (rotateSpeed - limitRotate < -rotateAccelerationLimit){
            limitRotate -= rotateAccelerationLimit;
        }else if (rotateSpeed - limitRotate <= rotateAccelerationLimit){
            limitRotate = rotateSpeed;
        }




        RobotMap.leftDriveMotor.set(ControlMode.PercentOutput, -limitRotate, DemandType.ArbitraryFeedForward, limitSpeed);
        RobotMap.rightDriveMotor.set(ControlMode.PercentOutput, +limitRotate, DemandType.ArbitraryFeedForward, limitSpeed);

    }
    //Tank Drive, one Joystick controls the left, one controls the right.
    public void tankDrive(double leftSpeed, double rightSpeed) {

        //Limits for speed, using quadratics and max/min
        leftSpeed = deadband(leftSpeed);
        rightSpeed = deadband(rightSpeed);

        limitSpeed = leftSpeed < 0 ? -0.8 : 0.8;
        limitRotate = rightSpeed < 0 ? -0.8 : 0.8;

        leftSpeed *= limitSpeed * leftSpeed;
        rightSpeed *= limitRotate * rightSpeed;

        //Tells the program to run the driveTank
      //  differentialDrive.tankDrive(leftSpeed, rightSpeed);
        RobotMap.rightDriveMotor.set(ControlMode.PercentOutput,rightSpeed);
        RobotMap.leftDriveMotor.set(ControlMode.PercentOutput,leftSpeed);

    }

    //The command won't work if you only bump the stick a little, a deadband in the middle
    private double deadband(double input) {
        if (Math.abs(input) < 0.2) {
            return 0;
        } else {
            return input;
        }
    }

    //Sets the default on startup command to be DriveMotion
    public void initDefaultCommand() {
        setDefaultCommand(new DriveMotion());
    }

}
