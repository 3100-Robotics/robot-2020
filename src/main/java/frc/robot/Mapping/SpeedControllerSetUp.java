
package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;

public class SpeedControllerSetUp {


    public void configure(){

        RobotMap.leftFrontDriveMotor.configFactoryDefault();
        RobotMap.rightFrontDriveMotor.configFactoryDefault();
        RobotMap.leftBackDriveMotor.configFactoryDefault();
        RobotMap.rightBackDriveMotor.configFactoryDefault();

        RobotMap.leftFrontDriveMotor.setInverted(true);
        RobotMap.rightFrontDriveMotor.setInverted(false);
        RobotMap.leftBackDriveMotor.setInverted(true);
        RobotMap.rightBackDriveMotor.setInverted(false);

        RobotMap.leftFrontDriveMotor.setNeutralMode(NeutralMode.Brake);
        RobotMap.rightFrontDriveMotor.setNeutralMode(NeutralMode.Brake);
        RobotMap.leftBackDriveMotor.setNeutralMode(NeutralMode.Brake);
        RobotMap.rightBackDriveMotor.setNeutralMode(NeutralMode.Brake);

        RobotMap.leftBackDriveMotor.follow(RobotMap.leftFrontDriveMotor);
        RobotMap.rightBackDriveMotor.follow(RobotMap.rightFrontDriveMotor);

        RobotMap.rightFrontDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
        RobotMap.rightFrontDriveMotor.config_kP(0, 0.06);
        RobotMap.rightFrontDriveMotor.config_kD(0, 0.0001 );
        RobotMap.rightFrontDriveMotor.config_kI(0, 0); //DON'T CHANGE
        RobotMap.rightFrontDriveMotor.config_kF(0, 1023.0/22425.0);

        RobotMap.leftFrontDriveMotor.setSelectedSensorPosition(0);

        

        
                            
                                                    
                                                    



    }

}