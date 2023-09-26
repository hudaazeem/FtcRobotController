package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


/**
 * Assignment :
 *
 * Navigate the robot thru the truss and park in front of of the board.
 *
 */

@Autonomous(name = "AutonomousAssignmentOne", group = "AutonomousAssignmentOne")
public class AutonomousAssignmentOne extends LinearOpMode {
        private DcMotor frontRight;
        private DcMotor frontLeft;
        private DcMotor backRight;
        private DcMotor backLeft;

        private DcMotor motor5;
        private DcMotor motor6;
        private DcMotor motor7;
        private DcMotor motor8;
        private DistanceSensor distanceSensor;
        private ColorSensor colorSensor;
        BNO055IMU imu;

        /**
         * This function is executed when this Op Mode is selected.
         */
        @Override
        public void runOpMode() {

                 final double HD_COUNTS_PER_REV = 28;

                 final double DRIVE_GEAR_REDUCTION = 20.15293;
                 final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
                 final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
                 final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;


                // Put initialization blocks here.
                frontRight = hardwareMap.get(DcMotor.class, "frontRight");
                frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
                backRight = hardwareMap.get(DcMotor.class, "backRight");
                backLeft = hardwareMap.get(DcMotor.class, "backLeft");

                motor7 = hardwareMap.get(DcMotor.class, "motor7");
                motor8 = hardwareMap.get(DcMotor.class, "motor8");

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

                imu = hardwareMap.get(BNO055IMU.class, "imu");
                imu.initialize(new BNO055IMU.Parameters());

                telemetry.addData("Status", "Initialized");
                telemetry.update();

                waitForStart();

                // move forward
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 23960 ) ; /* DRIVE_COUNTS_PER_MM * 100 */
                while (opModeIsActive()  && frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
                        frontRight.setPower(1);
                        frontLeft.setPower(1);
                        backRight.setPower(1);
                        backLeft.setPower(1);
                        telemetry.addData("Moving Forward to Target Position", frontRight.getTargetPosition());
                        telemetry.addData("Moving Forward my Current Position", frontRight.getCurrentPosition());
                        telemetry.update();
                }
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);
                motor8.setPower(-.5);
                motor7.setPower(.5);

                sleep(600);
                motor8.setPower(.5);


                // move left a little
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 5500 ) ; /* DRIVE_COUNTS_PER_MM * 100 */
                while (opModeIsActive()  && frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()) {
                        // strafe right
                        frontRight.setPower(-.5);
                        frontLeft.setPower(.5);
                        backRight.setPower(.5);
                        backLeft.setPower(-.5);
                        telemetry.addData("Moving Left to Target Position", frontLeft.getTargetPosition());
                        telemetry.addData("Moving Left my Current Position", frontLeft.getCurrentPosition());
                        telemetry.update();
                }

                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);
                // move forward again
                frontRight.setTargetPosition(frontRight.getCurrentPosition() + 18000 ) ; /* DRIVE_COUNTS_PER_MM * 100 */
                while (opModeIsActive()  && frontRight.getCurrentPosition() < frontRight.getTargetPosition()) {
                        frontRight.setPower(1);
                        frontLeft.setPower(1);
                        backRight.setPower(1);
                        backLeft.setPower(1);
                        telemetry.addData("Moving Forward to Target Position", frontRight.getTargetPosition());
                        telemetry.addData("Moving Forward my Current Position", frontRight.getCurrentPosition());
                        telemetry.update();
                }
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);

                // move left a little again
                frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 12000 ) ; /* DRIVE_COUNTS_PER_MM * 100 */

                while (opModeIsActive()  && frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()) {
                        // strafe right
                        frontRight.setPower(-.5);
                        frontLeft.setPower(.5);
                        backRight.setPower(.5);
                        backLeft.setPower(-.5);
                        telemetry.addData("Moving Left to Target Position", frontLeft.getTargetPosition());
                        telemetry.addData("Moving Left my Current Position", frontLeft.getCurrentPosition());

                        telemetry.update();
                }

                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);

                while (opModeIsActive()) {
                                         idle();

                }


        }
}