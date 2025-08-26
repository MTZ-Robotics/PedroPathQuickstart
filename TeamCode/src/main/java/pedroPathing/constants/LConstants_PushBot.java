package pedroPathing.constants;

import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerDegreeTurnChassis;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelDrive;
import static pedroPathing.constants.mtzConstants_ItD_pushBot.ticksPerInchWheelStrafe;

//import com.pedropathing.localization.OTOS;
import com.pedropathing.localization.constants.DriveEncoderConstants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class LConstants_PushBot {
    static {
        OTOSConstants.useCorrectedOTOSClass = true;
        OTOSConstants.hardwareMapName = "otos";
        OTOSConstants.linearUnit = DistanceUnit.INCH;
        OTOSConstants.angleUnit = AngleUnit.RADIANS;
        OTOSConstants.offset = new SparkFunOTOS.Pose2D(0, 0, Math.PI / 2);
        OTOSConstants.linearScalar = 1.1629;
        OTOSConstants.angularScalar = 1.0;
    }
}




