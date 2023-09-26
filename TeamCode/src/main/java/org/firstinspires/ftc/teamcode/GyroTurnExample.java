package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous
public class GyroTurnExample extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private BNO055IMU imu;
    private Orientation lastAngles = new Orientation();
    private double globalAngle;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        rightMotor = hardwareMap.get(DcMotor.class, "frontRight");

        // Initialize the IMU (gyro) sensor
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        waitForStart();
        // Stop the robot after turning
        leftMotor.setPower(1);
        rightMotor.setPower(-1);
        // Turn left 90 degrees
        turnByAngle(-90);

        // Stop the robot after turning
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    private void turnByAngle(double targetAngle) {
        double initialAngle = getAngle();

        while (opModeIsActive() && Math.abs(targetAngle - (getAngle() - initialAngle)) > 2) {
            double power = (targetAngle - (getAngle() - initialAngle)) / 60; // Adjust the divisor for desired sensitivity
            leftMotor.setPower(-power);
            rightMotor.setPower(power);
        }
    }

    private double getAngle() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;
        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;
        globalAngle += deltaAngle;
        lastAngles = angles;
        return globalAngle;
    }
}
