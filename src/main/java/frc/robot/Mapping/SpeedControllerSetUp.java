
package frc.robot.Mapping;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class SpeedControllerSetUp {


    public void configure(){

        RobotMap.leftDriveMotor.configFactoryDefault();
        RobotMap.rightDriveMotor.configFactoryDefault();

        RobotMap.leftDriveMotor.setInverted(true);
        RobotMap.rightDriveMotor.setInverted(false);

        RobotMap.leftDriveMotor.setNeutralMode(NeutralMode.Brake);
        RobotMap.rightDriveMotor.setNeutralMode(NeutralMode.Brake);

        RobotMap.leftDriveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        RobotMap.leftDriveMotor.config_kP(0, 0);
        RobotMap.leftDriveMotor.config_kD(0, 0);
        RobotMap.leftDriveMotor.config_kI(0, 0); //DON'T CHANGE
        RobotMap.leftDriveMotor.config_kF(0, 1023.0/7200.0);



    }

}