/*
Copyright 2021 FIRST Tech Challenge Team 0000

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station


 The config includes the following motors: 'frontLeft', 'frontRight', 'backLeft', 'backRight', 'motor5', 'motor6', 'motor7', 'motor8'


 */

public class BlankLinearOpMode2 extends LinearOpMode {


    // Declare motor variables
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {



        // Wait for the start button to be pressed on the driver station
        waitForStart();
        // Initialize motors
        leftMotor = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "frontRightDrive");

        // Reverse one of the motors if needed
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Your code to move the robot forward
        // Set motor power to move forward
        double power = 0.5; // You can adjust this value
        leftMotor.setPower(power);
        rightMotor.setPower(power);

        // Run the motors for a specified time (e.g., 2 seconds)
        sleep(2000);

        // Stop the motors
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // Optional: Perform other actions or tasks here

        // End the program
        requestOpModeStop();
    }
}
