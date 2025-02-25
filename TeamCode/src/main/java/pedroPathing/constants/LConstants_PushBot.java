package pedroPathing.constants;

import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerDegreeTurnChassis;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelDrive;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelStrafe;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.DriveEncoderConstants;


public class LConstants_PushBot {
    static {
        DriveEncoderConstants.forwardTicksToInches = ticksPerInchWheelDrive;
        DriveEncoderConstants.strafeTicksToInches = ticksPerInchWheelStrafe;
        DriveEncoderConstants.turnTicksToInches = ticksPerDegreeTurnChassis;

        DriveEncoderConstants.robot_Width = 12;
        DriveEncoderConstants.robot_Length = 7.56;

        DriveEncoderConstants.leftFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderConstants.rightFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.leftRearEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightRearEncoderDirection = Encoder.FORWARD;
    }
}




