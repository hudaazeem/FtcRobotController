package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@Autonomous

public class MyFIRSTJavaOpMode extends LinearOpMode {

    ColorSensor color1;
    DistanceSensor distance1;

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

        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");

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

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

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


        drive(120);

        // turn right 90 degrees
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + (48 * (int) DRIVE_COUNTS_PER_IN));

        telemetry.addData("backLeftDrive Current Position", backLeftDrive.getCurrentPosition());
        telemetry.addData("Target Position", backLeftDrive.getTargetPosition());
        telemetry.update();

        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(1);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("backLeftDrive Current Position", backLeftDrive.getCurrentPosition());
        telemetry.addData("Target Position", backLeftDrive.getTargetPosition());
        telemetry.update();

    }


    private void turn() {
    }

    private void drive(int distance) {

        //Convert from the counts per revolution of the encoder to counts per inch
        final double HD_COUNTS_PER_REV = 28;
        final double DRIVE_GEAR_REDUCTION = 20.15293;
        final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
        final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
        final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;


        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + (distance * (int) DRIVE_COUNTS_PER_IN));
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + (distance * (int) DRIVE_COUNTS_PER_IN));
        backRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + (distance * (int) DRIVE_COUNTS_PER_IN));
        backLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + (distance * (int) DRIVE_COUNTS_PER_IN));



        telemetry.addData("Initial Current Position", frontRightDrive.getCurrentPosition());
        telemetry.addData("Target Position", frontRightDrive.getTargetPosition());
        telemetry.update();

        // Your code to move the robot forward
        // Set motor power to move forward
        double power = 1; // You can adjust this value
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(power);

        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && frontRightDrive.isBusy() && frontRightDrive.getCurrentPosition() < frontRightDrive.getTargetPosition()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("Encoder Position", frontRightDrive.getCurrentPosition());
            telemetry.addData("Target Position", frontRightDrive.getTargetPosition());
            telemetry.update();
//            idle();
        }

        telemetry.addData("Status", "Stopping");
        telemetry.addData("Encoder Position", frontRightDrive.getCurrentPosition());
        telemetry.addData("Target Position", frontRightDrive.getTargetPosition());
        telemetry.update();
        power = 0; // You can adjust this value
        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(power);
        backLeftDrive.setPower(power);
        backRightDrive.setPower(power);
        idle();
        ;
    }
}
