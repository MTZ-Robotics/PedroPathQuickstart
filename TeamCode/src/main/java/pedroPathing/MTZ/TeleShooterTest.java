package pedroPathing.MTZ;
import static pedroPathing.MTZ.mtzConstants_ItD.armExtensionCollapsedLength;
import static pedroPathing.MTZ.mtzConstants_ItD.armExtensionInchesAtHome;
import static pedroPathing.MTZ.mtzConstants_ItD.armLengthDesired;
import static pedroPathing.MTZ.mtzConstants_ItD.armPivotHeight;
import static pedroPathing.MTZ.mtzConstants_ItD.armRotationDegreesAtHome;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultArmAssistLevel;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultArmExtensionPower;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultArmLowerPower;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultArmPower;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultDriveSpeed;
import static pedroPathing.MTZ.mtzConstants_ItD.defaultPauseTime;
import static pedroPathing.MTZ.mtzConstants_ItD.driveBump;
import static pedroPathing.MTZ.mtzConstants_ItD.driveFastRatio;
import static pedroPathing.MTZ.mtzConstants_ItD.driveSlowRatio;
import static pedroPathing.MTZ.mtzConstants_ItD.endGameOver;
import static pedroPathing.MTZ.mtzConstants_ItD.endGameStart;
import static pedroPathing.MTZ.mtzConstants_ItD.endGameWarning;
import static pedroPathing.MTZ.mtzConstants_ItD.endGameWarning2;
import static pedroPathing.MTZ.mtzConstants_ItD.findStackDistance;
import static pedroPathing.MTZ.mtzConstants_ItD.findStackLevel;
import static pedroPathing.MTZ.mtzConstants_ItD.greenWarningTime;
import static pedroPathing.MTZ.mtzConstants_ItD.handAssistRideHeightAboveLevel;
import static pedroPathing.MTZ.mtzConstants_ItD.handAssistRideHeightDistance;
import static pedroPathing.MTZ.mtzConstants_ItD.handAssistRideHeightLevel;
import static pedroPathing.MTZ.mtzConstants_ItD.launcherReleasePosition;
import static pedroPathing.MTZ.mtzConstants_ItD.launcherSetPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.leftClawClosedPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.leftClawOpenPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.maxArmDegrees;
import static pedroPathing.MTZ.mtzConstants_ItD.maxArmExtensionInches;
import static pedroPathing.MTZ.mtzConstants_ItD.maxWristPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.minArmDegrees;
import static pedroPathing.MTZ.mtzConstants_ItD.minArmExtensionInches;
import static pedroPathing.MTZ.mtzConstants_ItD.minWristPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.prorate;
import static pedroPathing.MTZ.mtzConstants_ItD.redWarningTime;
import static pedroPathing.MTZ.mtzConstants_ItD.rightClawClosedPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.rightClawOpenPosition;
import static pedroPathing.MTZ.mtzConstants_ItD.scoopStage;
import static pedroPathing.MTZ.mtzConstants_ItD.stackDistanceArray;
import static pedroPathing.MTZ.mtzConstants_ItD.stackDistanceAtHome;
import static pedroPathing.MTZ.mtzConstants_ItD.stackHeightAboveLevelArray;
import static pedroPathing.MTZ.mtzConstants_ItD.stackHeightOnLevelArray;
import static pedroPathing.MTZ.mtzConstants_ItD.stackLevelAtHome;
import static pedroPathing.MTZ.mtzConstants_ItD.strafeBump;
import static pedroPathing.MTZ.mtzConstants_ItD.ticksPerDegreeArm;
import static pedroPathing.MTZ.mtzConstants_ItD.ticksPerDegreeTurnChassis;
import static pedroPathing.MTZ.mtzConstants_ItD.ticksPerInchExtension;
import static pedroPathing.MTZ.mtzConstants_ItD.ticksPerInchWheelDrive;
import static pedroPathing.MTZ.mtzConstants_ItD.ticksPerInchWheelStrafe;
import static pedroPathing.MTZ.mtzConstants_ItD.turnBump;
import static pedroPathing.MTZ.mtzConstants_ItD.wristAdjustment;
import static pedroPathing.MTZ.mtzConstants_ItD.wristBump;
import static pedroPathing.MTZ.mtzConstants_ItD.wristConversionToServo;
import static pedroPathing.MTZ.mtzConstants_ItD.yellowWarningTime;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Objects;
@TeleOp(name="Tele Shooter Test", group ="Test")
//@Disabled
/****  Test Shooter with speed and trigger controls */

public class TeleShooterTest extends LinearOpMode {
    double topFlywheelRatio = 0.75;
    double bottomFlywheelDesired = 0.9;
    /********************************
     * Timer Variables
     ********************************/
    private ElapsedTime endGameTimer;
    boolean endGameStartElapsed;
    /*************************
     * Motor & Servo Variables
     *************************/
    private DcMotor bottomFlywheel;
    private DcMotor topFlywheel;
    private Servo trigger;
    /*******
     * Add Controller Variables & Objects
     ********/
// Assign Variables & Objects for Control Pads
    double triggerValue;
    mtzButtonBehavior topFlywheelFaster = new mtzButtonBehavior();
    mtzButtonBehavior bottomFlywheelFaster = new mtzButtonBehavior();
    mtzButtonBehavior topFlywheelSlower = new mtzButtonBehavior();
    mtzButtonBehavior bottomFlywheelSlower = new mtzButtonBehavior();
// End of Assignment Mapping
    @Override
    //This is the default opMode call for generically running the opMode in this class directly from the phone without calling it from a super class
    public void runOpMode() throws InterruptedException{
        endGameStartElapsed = false;

        bottomFlywheel = hardwareMap.dcMotor.get("m5");
        topFlywheel = hardwareMap.dcMotor.get("m6");
        bottomFlywheel.setDirection(DcMotor.Direction.REVERSE);
        topFlywheel.setDirection(DcMotor.Direction.REVERSE);
        bottomFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        topFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        trigger = hardwareMap.servo.get("s1");
        trigger.setDirection(Servo.Direction.REVERSE);

        /**********************************
         * Do Not set positions on initialize since that counts as controlling the robot
         * and initialize would not be able to happen until the timer starts for driver controlled period
         **********************************/
        /***********************************************
         * Tell driver station that initialization is complete
         **********************************************/
        telemetry.log().clear();
        telemetry.update();
        telemetry.log().add("Initialized. ");
        waitForStart();
        while (opModeIsActive()) {
            /**************************************************************
             *
             * TeleOp Loops From Here to the End of controlRobot
             *
             * Loops often to see if controls are still the same
             *
             ****************************************************************/
            triggerValue = gamepad1.right_trigger;
            bottomFlywheelFaster.update(gamepad1.dpad_up);
            topFlywheelFaster.update(gamepad1.dpad_left);
            topFlywheelSlower.update(gamepad1.dpad_right);
            bottomFlywheelSlower.update(gamepad1.dpad_down);

            if(topFlywheelFaster.clickedDown){topFlywheelRatio=topFlywheelRatio*1.05;}
            if(topFlywheelSlower.clickedDown){topFlywheelRatio=topFlywheelRatio*0.95;}
            if(bottomFlywheelFaster.clickedDown){bottomFlywheelDesired=bottomFlywheelDesired*1.05;}
            if(bottomFlywheelSlower.clickedDown){bottomFlywheelDesired=bottomFlywheelDesired*0.95;}
            bottomFlywheel.setPower(bottomFlywheelDesired);
            topFlywheel.setPower(topFlywheelRatio*bottomFlywheelDesired);

            bottomFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            topFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            trigger.setPosition(triggerValue);

            displayTelemetry();
        }
    }
//Telemetry Methods
    public void displayTelemetry() {
        telemetry.clearAll();
        telemetry.addLine()
                .addData("Timer: ", endGameTimer.toString());
            telemetry.addLine()
                    .addData("Bottom: ", bottomFlywheel.getPower());
            telemetry.addLine()
                    .addData("Top: ", topFlywheel.getPower());
            telemetry.addLine()
                    .addData("Trigger: ", trigger.getPosition());
    }

    //End of Telemetry Methods
    //End of Class
}
