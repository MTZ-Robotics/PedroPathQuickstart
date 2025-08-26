package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelIMUConstants.forwardTicksToInches = .00312;
        ThreeWheelIMUConstants.strafeTicksToInches = .00312;
        ThreeWheelIMUConstants.turnTicksToInches = .003;
        ThreeWheelIMUConstants.leftY =  3.75;
        ThreeWheelIMUConstants.rightY = -3.875;
        ThreeWheelIMUConstants.strafeX = 4.25;
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "rightRear";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "leftFront";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }
}




