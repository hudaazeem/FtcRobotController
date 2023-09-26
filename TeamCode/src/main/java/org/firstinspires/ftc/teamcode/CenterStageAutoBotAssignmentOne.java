package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Example Autonomous Opmode
 *
 * Uses Line-following two drive around the tape at the perimeter of the lander.
 *
 * Requires mechanum bot configuration.
 *
 * Start with bot in center of lander, facing top of screen.
 *
 * Disabling for now; it was designed to work with Rover Ruckus field
 *
 */

@Autonomous(name = "CenterStageAutoBotAssignmentOne", group = "Mechanum")
public class CenterStageAutoBotAssignmentOne extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    private DcMotor motor5;
    private DcMotor motor7;

    private DistanceSensor distanceSensor;
    private ColorSensor colorSensor;
    BNO055IMU imu;

    /**
     * This function is executed when this Op Mode is selected.
     */
    @Override
    public void runOpMode() {
        // Put initialization blocks here.
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        motor5 = hardwareMap.get(DcMotor.class, "motor8");
        motor7 = hardwareMap.get(DcMotor.class, "motor7");


//         ticksPerRotation = motor.getMotorType().getTicksPerRev();

//        backLeft = hardwareMap.dcMotor.get("backLeft");
//        frontLeft = hardwareMap.dcMotor.get("frontLeft");
//        frontRight = hardwareMap.dcMotor.get("frontRight");
//        backRight = hardwareMap.dcMotor.get("backRight");

//        ticksPerRotation = frontRight.getMotorType().getTicksPerRev();


        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);



        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());

//        telemetry.addData("ticksPerRotation", ticksPerRotation);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 23960 /* DRIVE_COUNTS_PER_MM * 100 */ );

            // forward
            frontRight.setPower(1);
            frontLeft.setPower(1);
            backRight.setPower(1);
            backLeft.setPower(1);

            sleep(3200); // while program ends at 1000, this continues

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            motor5.setPower(-.5);
            motor7.setPower(.5);

            sleep(600);

            // reverse motors again after program got ended
            //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            //backRight.setDirection(DcMotorSimple.Direction.REVERSE)



            // strafe right
            frontRight.setPower(-.5);
            frontLeft.setPower(.5);
            backRight.setPower(.5);
            backLeft.setPower(-.5);
            sleep(1500);

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(600);


//            sleep(1000); // following sleeps don't affect running

            // test and see opmode is active
            telemetry.addData("isActive", opModeIsActive());
            telemetry.update();


            // forward
            frontRight.setPower(1);
            frontLeft.setPower(1);
            backRight.setPower(1);
            backLeft.setPower(1);

            sleep(2000); // while program ends at 1000, this continues

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(600);


            // strafe right again
            frontRight.setPower(-.5);
            frontLeft.setPower(.5);
            backRight.setPower(.5);
            backLeft.setPower(-.5);
            sleep(1500);

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(600);

//            // strafe left
//            frontRight.setPower(.5);
//            frontLeft.setPower(-.5);
//            backRight.setPower(-.5);
//            backLeft.setPower(.5);
//            sleep(1000);

            sleep(500); // giving time for the third strafe
        }
    }
}