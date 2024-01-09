package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is an example minimal implementation of the mecanum drivetrain
 * for demonstration purposes.  Not tested and not guaranteed to be bug free.
 *
 * @author Brandon Gong
 */
public class Autonomous_Simple2023 extends LinearOpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor       RightFront    = null;
    private DcMotor       LeftFront     = null;
    private DcMotor       LeftBack      = null;
    private DcMotor       RightBack     = null;
    private DcMotor       LinearSlideL  = null;
    private DcMotor       LinearSlideR  = null;
    private DcMotor       Intake        = null;
    private Servo         PlaneLaunch   = null;
    private ElapsedTime   runtime       = new ElapsedTime();

     @Override
    public void runOpMode() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        RightFront   = hardwareMap.get(DcMotor.class, "mtr_rf");
        LeftFront    = hardwareMap.get(DcMotor.class, "mtr_lf");
        LeftBack     = hardwareMap.get(DcMotor.class, "mtr_lb");
        RightBack    = hardwareMap.get(DcMotor.class, "mtr_rb");
        LinearSlideL = hardwareMap.get(DcMotor.class, "mtr_LinL");
        LinearSlideR = hardwareMap.get(DcMotor.class, "mtr_LinR");
        Intake       = hardwareMap.get(DcMotor.class, "mtr_intake");
        PlaneLaunch  = hardwareMap.get(Servo.class,   "Airplane_Launcher");


        
        
        PlaneLaunch.setPosition(0.67);
        
        
        
        waitForStart();
        
        
        LeftFront.setPower  (50);
        RightFront.setPower (50);
        LeftBack.setPower   (50);        
        RightBack.setPower  (50);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        
        LeftFront.setPower  (50);
        RightFront.setPower (-50);
        LeftBack.setPower   (50);        
        RightBack.setPower  (-50);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        
        
        
        
        
    }
}