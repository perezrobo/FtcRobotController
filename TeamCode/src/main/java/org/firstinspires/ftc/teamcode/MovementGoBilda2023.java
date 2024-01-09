package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This is an example minimal implementation of the mecanum drivetrain
 * for demonstration purposes.  Not tested and not guaranteed to be bug free.
 *
 * @author Brandon Gong
 */
@TeleOp(name="MovementGoBilda2023", group="Iterative Opmode")
public class MovementGoBilda2023 extends OpMode {

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
    /*private DcMotor LinearSlideL  = null;
    private DcMotor LinearSlideR  = null;
    private DcMotor Intake        = null;
    private Servo   PlaneLaunch   = null;
    private CRServo DeliveryL     = null;
    private CRServo DeliveryR     = null;
    boolean SwitchIn              = false;
    boolean SwitchOut             = false;
    boolean DeliveryOn            = false;
    boolean DeliveryOff           = false;*/
    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        RightFront   = hardwareMap.get(DcMotor.class, "mtr_rf");
        LeftFront    = hardwareMap.get(DcMotor.class, "mtr_lf");
        LeftBack     = hardwareMap.get(DcMotor.class, "mtr_lb");
        RightBack    = hardwareMap.get(DcMotor.class, "mtr_rb");
        /*LinearSlideL = hardwareMap.get(DcMotor.class, "mtr_LinL");
        LinearSlideR = hardwareMap.get(DcMotor.class, "mtr_LinR");
        Intake       = hardwareMap.get(DcMotor.class, "mtr_intake");
        PlaneLaunch  = hardwareMap.get(Servo.class,   "srvo_plane");
        DeliveryL    = hardwareMap.get(CRServo.class, "srvo_DelivL");
        DeliveryR    = hardwareMap.get(CRServo.class, "srvo_DelivR");


      
        PlaneLaunch.setPosition(0.67);*/
    }

    @Override
    public void loop() {
    if (gamepad2.right_trigger > 0){
        
    }
        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        
        // Directions are inverted because of how the GoBilda motors are set up.
        double y  = gamepad1.left_stick_y;
        double x  = -gamepad1.left_stick_x*1.1;
        double rx  = -gamepad1.right_stick_x;
        
        


        double[] speeds = {
            /*(y + x - rx) ,
            (y - x - rx) ,
            (y - x + rx) ,
            (y + x + rx)
            
            ^ (Pre scrim code, didn't work but might have been an external problem.)
            */
            (y + x + rx),
            (y - x + rx),
            (y - x - rx),
            (y + x - rx)
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
        
        
        //Right powers are inverted because of the GoBilda motor mechanism?
        LeftFront.setPower(speeds[0]);
        LeftBack.setPower(speeds[1]);
        RightFront.setPower(-speeds[2]);
        RightBack.setPower(-speeds[3]);
        
        /*Panning the linear slide in and out
            Right trigger to go up
            Left trigger to go in 
        */
        /*if (gamepad2.right_trigger > 0){
            LinearSlideL.setPower(-gamepad2.right_trigger);
            LinearSlideR.setPower(gamepad2.right_trigger);
        }*/
        
        /*if (gamepad2.left_trigger > 0){
            LinearSlideL.setPower(gamepad2.left_trigger);
            LinearSlideR.setPower(-gamepad2.left_trigger);
        }*/
        
        
        
        /*Turning the intake in and out a button) 
            a to suck in 
            b to push out */
        //If intake is moving, press the same button again to turn SwitchIn to false and stop movement
        /*if (gamepad2.a == true && SwitchIn == false){
            Intake.setPower(100);
            SwitchIn = true;
        }
        
        if (gamepad2.a == true && SwitchIn == true){
            Intake.setPower(0);
            SwitchIn = false;
        }
        
        if (gamepad2.b == true && SwitchOut == false){
            Intake.setPower(-100);
            SwitchOut = true;
        }
        
        if (gamepad2.b == true && SwitchOut == true){
            Intake.setPower(0);
            SwitchOut = false;
        }*/
       
       
       
        //Spinning the delivery servos in (Left Bumper)
        /*if (gamepad2.left_bumper == true && DeliveryOn == false){
            DeliveryL.setPower(1);
            DeliveryR.setPower(1);
            DeliveryOn = true;
        }
        if (gamepad2.left_bumper == true && DeliveryOn == true){
            DeliveryL.setPower(0.5);
            DeliveryR.setPower(0.5);
            DeliveryOn = false;
        }*/
        
        
        
        //Spinning delivery servos out (Right Bumper)
        /*if (gamepad2.left_bumper == true && DeliveryOff == false){
            DeliveryL.setPower(0);
            DeliveryR.setPower(0);
            DeliveryOff = true;
        }
        if (gamepad2.left_bumper == true && DeliveryOff == true){
            DeliveryL.setPower(0.5);
            DeliveryR.setPower(0.5);
            DeliveryOff = false;
        }*/
        
        // Launching the paper plane
        
        /*if (gamepad2.x == true){
            PlaneLaunch.setPosition(0.85);
        }
        if(gamepad2.y == true){
            PlaneLaunch.setPosition(0.67);
        }*/
        
        
 
        
        
        
        
        
        
        
   
        
       
    }
}
