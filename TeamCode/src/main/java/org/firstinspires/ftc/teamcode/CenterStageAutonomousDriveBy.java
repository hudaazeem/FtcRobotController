package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous

public class CenterStageAutonomousDriveBy extends LinearOpMode {

    // Declare motors
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private BNO055IMU imu;


    //Convert from the counts per revolution of the encoder to counts per inch
    static final double HD_COUNTS_PER_REV = 28;

    static final double DRIVE_GEAR_REDUCTION = 20.15293;
    static final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
    static final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
    static final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;

    double globalAngle;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        imu = hardwareMap.get(BNO055IMU.class, "imu");



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



        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // Initialize the IMU (gyro) sensor
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        imu.initialize(parameters);

        telemetry.addData("Gyro Mode", "calibrating...");

        telemetry.update();


//        while (!isStopRequested() && !imu.isGyroCalibrated()) {
//            sleep(50);
//            idle();
//        }
//
//        telemetry.addData("Gyro Mode", "ready");
//
//        telemetry.addData("imu calibration status", imu.getCalibrationStatus().toString());
//
//        telemetry.update();
        if (opModeIsActive()){

            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 23960 /* DRIVE_COUNTS_PER_MM * 100 */ );

            // Your code to move the robot forward
            // Set motor power to move forward
            double power = 1; // You can adjust this value
            frontLeft.setPower(power);
            frontRight.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(power);

            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        while (opModeIsActive()  && frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
            telemetry.addData("imu", imu.getAngularOrientation().firstAngle);
            telemetry.addData("motor busy",frontRight.isBusy() );
            telemetry.addData("Status", "Running");
            telemetry.addData("Encoder Position", frontRight.getCurrentPosition());
            telemetry.update();
        }

            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);

            telemetry.addData("Status", "Stopping");
            telemetry.addData("Encoder Position", frontRight.getCurrentPosition());
            telemetry.addData("Target Position", frontRight.getTargetPosition());
            telemetry.update();

        telemetry.addData("Now Turning left at ", frontRight.getTargetPosition());
        telemetry.update();


        frontRight.setTargetPosition(frontRight.getCurrentPosition() + 13000 /* DRIVE_COUNTS_PER_MM * 100 */ );
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setPower(1);
        frontLeft.setPower(1);
        backRight.setPower(1);
        backLeft.setPower(1);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setPower(0);
        frontRight.setPower(1);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setDirection(DcMotor.Direction.FORWARD);


        while (opModeIsActive() && frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {

            telemetry.addData("Target Position", frontRight.getTargetPosition());
            telemetry.update();
        }

        telemetry.addData("left turn completed now moving forward ", frontRight.getTargetPosition());
        telemetry.update();

        if (opModeIsActive()){


            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.REVERSE);

            frontRight.setTargetPosition(frontRight.getCurrentPosition() + 23960 /* DRIVE_COUNTS_PER_MM * 100 */ );
//            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Your code to move the robot forward
            // Set motor power to move forward
            double power = 1; // You can adjust this value
            frontLeft.setPower(power);
            frontRight.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(power);
//            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        while (opModeIsActive()  && frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
            telemetry.addData("imu", imu.getAngularOrientation().firstAngle);
            telemetry.addData("motor busy",frontRight.isBusy() );
            telemetry.addData("Status", "Running");
            telemetry.addData("Encoder Position", frontRight.getCurrentPosition());
            telemetry.update();
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        telemetry.addData("Status", "Stopping");
        telemetry.addData("Encoder Position", frontRight.getCurrentPosition());
        telemetry.addData("Target Position", frontRight.getTargetPosition());
        telemetry.update();
            idle();

    }
}
