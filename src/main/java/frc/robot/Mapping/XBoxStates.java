
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.Joystick;

public class XBoxStates extends Joystick {

    public XBoxStates(int port) {
        super(port);
    }

    // set up methods to retrieve the values from variable inputs on the controller.

    public double getRightStickX() {
        return getRawAxis(RobotMap.rightXAxisChannel);
    }

    public double getRightStickY() {
        return getRawAxis(RobotMap.rightYAxisChannel);
    }

    public double getLeftStickX() {
        return getRawAxis(RobotMap.leftXAxisChannel);
    }

    public double getLeftStickY() {
        return getRawAxis(RobotMap.leftYAxisChannel);
    }

    public boolean getButtonA() {
        return getRawButton(RobotMap.aButtonChannel);
    }

    public boolean getButtonB() {
        return getRawButton(RobotMap.bButtonChannel);
    }

    public boolean getButtonX() {
        return getRawButton(RobotMap.xButtonChannel);
    }

    public boolean getButtonY() {
        return getRawButton(RobotMap.yButtonChannel);
    }

    public boolean getRightBumper() {
        return getRawButton(RobotMap.rightBumperChannel);
    }

    public boolean getLeftBumper() {
        return getRawButton(RobotMap.leftBumperChannel);
    }

    public double getRightTrigger() {
        return getRawAxis(RobotMap.rightTriggerChannel);
    }

    public double getLeftTrigger() {
        return getRawAxis(RobotMap.leftTriggerChannel);
    }

    public boolean getPOVD() {
        return (getPOV() == 270);
    }

    public boolean getPOVL() {
        return (getPOV() == 180);
    }

    public boolean getPOVR() {
        return (getPOV() == 0);
    }

    public boolean getPOVU() {
        return (getPOV() == 90);
    }

	public void arcadeDrive(double x, double y, double currentRotationRate, double angle) {
	}


}