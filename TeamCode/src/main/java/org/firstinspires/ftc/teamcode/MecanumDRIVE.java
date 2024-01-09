package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This is an example minimal implementation of the mecanum drivetrain
 * for demonstration purposes.  Not tested and not guaranteed to be bug free.
 *
 * @author Brandon Gong
 */
@TeleOp(name="MecanumDRIVE", group="Iterative Opmode")
public class MecanumDRIVE extends OpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor RightFront    = null;
    private DcMotor LeftFront     = null;
    private DcMotor LeftBack      = null;
    private DcMotor RightBack     = null;
    private DcMotor LinearSlideL  = null;
    private DcMotor LinearSlideR  = null;
    private DcMotor Intake        = null;
    private DcMotor Puller        = null;
    private Servo   PincerL       = null;
    private Servo   PincerR       = null;
    private Servo   PlaneLaunch   = null;
    private Servo   Dropper       = null;

    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        RightFront   = hardwareMap.get(DcMotor.class, "mtr_rf");
        LeftFront    = hardwareMap.get(DcMotor.class, "mtr_lf");
        LeftBack     = hardwareMap.get(DcMotor.class, "mtr_lb");
        RightBack    = hardwareMap.get(DcMotor.class, "mtr_rb");
        LinearSlideL = hardwareMap.get(DcMotor.class, "mtr_LinL");
        LinearSlideR = hardwareMap.get(DcMotor.class, "mtr_LinR");
        Intake       = hardwareMap.get(DcMotor.class, "mtr_intake");
        PincerL      = hardwareMap.get(Servo.class,   "srvo_Lpin");
        PincerR      = hardwareMap.get(Servo.class,   "srvo_Rpin");
        PlaneLaunch  = hardwareMap.get(Servo.class,   "srvo_plane");
        Dropper      = hardwareMap.get(Servo.class,   "srvo_dropper");
        Puller       = hardwareMap.get(DcMotor.class, "mtr_pull");

        //LeftFront.setDirection(DcMotor.Direction.REVERSE);
        //LeftBack.setDirection(DcMotor.Direction.REVERSE);
        //RightFront.setDirection(DcMotor.Direction.REVERSE);
        //RightBack.setDirection(DcMotor.Direction.FORWARD);
        
        
        //servo positions from 0 to 1
        
        PincerL.setPosition(0.35);
        PincerR.setPosition(0.65);
        PlaneLaunch.setPosition(0.4);
        Dropper.setPosition(0.5);
        telemetry.addLine("Hello, world!");
        telemetry.addLine("Oliver smells");
        
        
    }

    @Override
    public void loop() {

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        double y  = -gamepad1.left_stick_y;
        double x  = gamepad1.left_stick_x;
        double rx  = gamepad1.right_stick_x;
        
        

        /*
         * If we had a gyro and wanted to do field-oriented control, here
         * is where we would implement it.
         *
         * The idea is fairly simple; we have a robot-oriented Cartesian (x,y)
         * coordinate (strafe, drive), and we just rotate it by the gyro
         * reading minus the offset that we read in the init() method.
         * Some rough pseudocode demonstrating:
         *
         * if Field Oriented Control:
         *     get gyro heading
         *     subtract initial offset from heading
         *     convert heading to radians (if necessary)
         *     new strafe = strafe * cos(heading) - drive * sin(heading)
         *     new drive  = strafe * sin(heading) + drive * cos(heading)
         *
         * If you want more understanding on where these rotation formulas come
         * from, refer to
         * https://en.wikipedia.org/wiki/Rotation_(mathematics)#Two_dimensions
         */

        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        // y values are up and down on the left stick
        // x values are left and right on the left stick
        // rx values are left and right on the right stick
        // swap these after swapping list numbers in motor power section below
        double[] speeds = {
            (y - x + rx) ,
            (y + x + rx) ,
            (y + x - rx) ,
            (y - x - rx)
        };

        // Because we are adding vectors and motors only take values between
        // [-1,1] we may need to normalize them.

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        LeftFront.setPower(-speeds[0]);
        LeftBack.setPower(speeds[1]);
        RightFront.setPower(speeds[2]);
        RightBack.setPower(speeds[3]);
        
//Coding the linear slide
        if (gamepad2.right_trigger > 0){
            LinearSlideL.setPower(-gamepad2.right_trigger);
            LinearSlideR.setPower(gamepad2.right_trigger);
        }
        
        if (gamepad2.right_trigger == 0){
            LinearSlideL.setPower(0);
            LinearSlideR.setPower(0);
        }
        
        if (gamepad2.left_trigger > 0){
            LinearSlideL.setPower(gamepad2.left_trigger);
            LinearSlideR.setPower(-gamepad2.left_trigger);
        }
        
        if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
            LinearSlideL.setPower(0);
            LinearSlideR.setPower(0);
        }
        
        if (gamepad2.a == true){
            Intake.setPower(100);
            
        }
        
        if (gamepad2.a == false){
            Intake.setPower(0);
        }
        
        
        if (gamepad2.b == true){
            Intake.setPower(-100);
            
        }
        
        if (gamepad2.b == false){
            Intake.setPower(0);
        }
        
        
        if (gamepad2.x == true){
            PincerL.setPosition(0.35);
            PincerR.setPosition(0.65);
        }
        
        if(gamepad2.y == true){
            PincerL.setPosition(0.65);
            PincerR.setPosition(0.35);
        }
        
        if(gamepad2.dpad_up == true){
            PlaneLaunch.setPosition(1.0);
        }
        
        if(gamepad2.left_bumper == true){
            Dropper.setPosition(0.45);
        }
        
        if(gamepad2.right_bumper == true){
            Dropper.setPosition(0.55);
            
        }
   
        if(gamepad2.dpad_down == true){
            Dropper.setPosition(0.5);
        }
       
       if(gamepad2.dpad_right == true){
            Puller.setPower(100);
        }
        else{
            Puller.setPower(0);
        }
        
        if(gamepad2.dpad_left == true){
            Puller.setPower(-100);
        }
        else{
            Puller.setPower(0);
        }
        
        
    }   
}

