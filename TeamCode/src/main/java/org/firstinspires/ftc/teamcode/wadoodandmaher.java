package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Autonomous
public class wadoodandmaher extends LinearOpMode {

    // Define your hardware components here
    private DcMotorEx frontright;
    private DcMotorEx backright;
    private DcMotorEx frontleft;
    private DcMotorEx backleft;

    // Define constants and variables for Autonomous movement

    public void runOpMode() throws InterruptedException {
        // Initialize your hardware components here

//         double WHEEL_CIRCUMFERENCE_CM = 22/7/* Your wheel circumference in cm */;
//        int ENCODER_TICKS_PER_ROTATION = 115/* Your encoder ticks per rotation */;
//         int TARGET_POSITION_CM = 75; // Target distance in cm for Autonomous

        frontleft = hardwareMap.get(DcMotorEx.class, "frontleft");
        frontright = hardwareMap.get(DcMotorEx.class, "frontright");
        backleft = hardwareMap.get(DcMotorEx.class, "backleft");
        backright = hardwareMap.get(DcMotorEx.class, "backright");
        frontleft.setDirection(DcMotorEx.Direction.FORWARD);
        backleft.setDirection(DcMotorEx.Direction.FORWARD);
        frontright.setDirection(DcMotorEx.Direction.REVERSE);
        backright.setDirection(DcMotorEx.Direction.REVERSE);

        frontleft.setDirection(DcMotorEx.Direction.FORWARD);
        backleft.setDirection(DcMotorEx.Direction.FORWARD);
        frontright.setDirection(DcMotorEx.Direction.REVERSE);
        backright.setDirection(DcMotorEx.Direction.REVERSE);

        frontright.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        frontleft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        backright.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        backleft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);



        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // ...

        waitForStart();

        // Autonomous code starts here
        // Assuming your robot's wheel circumference is in cm and you have encoder ticks per rotation
//        int targetPosition = (int) ((TARGET_POSITION_CM / WHEEL_CIRCUMFERENCE_CM) * ENCODER_TICKS_PER_ROTATION);

            int targetPosition = frontleft.getCurrentPosition() + 2746 ;
        // Move forward with a power of 0.5 (adjust as needed)
//        moveForward(0.5, targetPosition);
        double power = .5;

        // Set target positions
        frontleft.setTargetPosition(targetPosition);
        frontright.setTargetPosition(targetPosition);
        backleft.setTargetPosition(targetPosition);
        backright.setTargetPosition(targetPosition);

        // Set mode to run to position
        frontleft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // Set power
        frontleft.setPower(.9);
        frontright.setPower(power);
        backleft.setPower(.9);
        backright.setPower(power);

        // Wait until the motors reach their target position
        while (frontleft.isBusy() && frontright.isBusy() && backleft.isBusy() && backright.isBusy()) {
            // You can add telemetry data here to monitor progress if needed
        }

        // Stop the motors
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        // Reset motor modes to normal
        frontleft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // Autonomous code ends here
    }


}