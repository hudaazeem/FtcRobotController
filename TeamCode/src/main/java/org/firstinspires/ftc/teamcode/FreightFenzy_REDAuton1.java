package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class FreightFenzy_REDAuton1 extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;

    //Convert from the counts per revolution of the encoder to counts per inch


    //Create elapsed time variable and an instance of elapsed time
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {



        // Initialize motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");


        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        if (opModeIsActive()) {

            //segment 1
            drive(0.7, 30, 15, frontLeft, frontRight);

            runtime.reset(); // reset elapsed time timer

            //segment 2 - lift arm, drive to shipping hub, outtake freight
            while (opModeIsActive() && runtime.seconds() <= 7) {


                //drive forward for 1 second
                while (runtime.seconds() > 2 && runtime.seconds() <= 3) {
                    drive(0.4, 4, 4, frontLeft, frontRight);
                }


                //segment 3 - reverse to get better angle
                drive(0.7, -15, -30, frontLeft, frontRight);

                //segment 4 - drive into warehouse
                drive(1, 90, 90, frontLeft, frontRight);
            }
        }
    }


    // Drive function with 3 parameters
    private void drive(double power, double leftInches, double rightInches, DcMotor frontLeft, DcMotor frontRight) {
        int rightTarget;
        int leftTarget;
         double HD_COUNTS_PER_REV = 28;
         double DRIVE_GEAR_REDUCTION = 20.15293;
         double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
         double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
         double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;
        if (opModeIsActive()) {
            // Create target positions
            rightTarget = frontRight.getCurrentPosition() + (int) (rightInches * DRIVE_COUNTS_PER_IN);
            leftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * DRIVE_COUNTS_PER_IN);

            // set target position
            frontLeft.setTargetPosition(leftTarget);
            frontRight.setTargetPosition(rightTarget);

            //switch to run to position mode
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //run to position at the desiginated power
            frontLeft.setPower(power);
            frontRight.setPower(power);

            // wait until both motors are no longer busy running to position
            while (opModeIsActive() && (frontLeft.isBusy() || frontRight.isBusy())) {
            }

            // set motor power back to 0
            frontLeft.setPower(0);
            frontRight.setPower(0);
        }
    }


}