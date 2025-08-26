package pedroPathing.constants;

import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerDegreeTurnChassis;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelDrive;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelStrafe;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.DriveEncoderConstants;


public class LConstants_Inky {
    static {
        DriveEncoderConstants.forwardTicksToInches = 1/ticksPerInchWheelDrive;
        DriveEncoderConstants.strafeTicksToInches = 1/ticksPerInchWheelStrafe;
        DriveEncoderConstants.turnTicksToInches = 1/ticksPerDegreeTurnChassis;

        DriveEncoderConstants.robot_Width = 12;
        DriveEncoderConstants.robot_Length = 7.56;

        DriveEncoderConstants.leftFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.leftRearEncoderDirection = Encoder.FORWARD;
        DriveEncoderConstants.rightRearEncoderDirection = Encoder.FORWARD;
    }
}




