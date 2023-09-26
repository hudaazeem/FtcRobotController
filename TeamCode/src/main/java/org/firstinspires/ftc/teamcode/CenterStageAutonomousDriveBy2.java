package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class CenterStageAutonomousDriveBy2 extends LinearOpMode {

    CRServo leftWheel;
    CRServo rightWheel;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor wrist;
    DcMotor leftShoulder;
    DcMotor rightShoulder;
    BNO055IMU imu;



    @Override
    public void runOpMode() throws InterruptedException {

        //Convert from the counts per revolution of the encoder to counts per inch
         final double HD_COUNTS_PER_REV = 28;
         final double DRIVE_GEAR_REDUCTION = 20.15293;
         final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
         final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
         final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;


        leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
        rightWheel = hardwareMap.get(CRServo.class, "rightWheel");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        leftShoulder = hardwareMap.get(DcMotor.class, "leftShoulder");
        rightShoulder = hardwareMap.get(DcMotor.class, "rightShoulder");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Put initialization blocks here
        waitForStart();

        if (opModeIsActive()){

            frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + ( 18 * 1200 ));

            // Your code to move the robot forward
            // Set motor power to move forward
            double power = 1; // You can adjust this value
            frontLeftDrive.setPower(power);
            frontRightDrive.setPower(power);
            backLeftDrive.setPower(power);
            backRightDrive.setPower(power);

            frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        // Put run blocks here
//        while (opModeIsActive()) {
//
//            telemetry.addData("Status", "Running");
//            telemetry.addData("Encoder Position", frontRightDrive.getCurrentPosition());
//            telemetry.update();
//
//
//            if (frontRightDrive.getCurrentPosition() >= frontRightDrive.getTargetPosition()) {
//                frontRightDrive.setPower(0);
//                frontLeftDrive.setPower(0);
//                backRightDrive.setPower(0);
//                backLeftDrive.setPower(0);
//
//                telemetry.addData("Status", "Stopping");
//                telemetry.addData("Encoder Position", frontRightDrive.getCurrentPosition());
//                telemetry.addData("Target Position", frontRightDrive.getTargetPosition());
//                telemetry.update();
//                idle();
//                break;
//            }
//
//        }

    }
}
