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
@TeleOp(name="TestDrive", group="Iterative Opmode")
public class TestDrive extends OpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize DcMotors and servos.
    private DcMotor RightFront  = null;
    private DcMotor LeftFront   = null;
    private DcMotor LeftBack    = null;
    private DcMotor RightBack   = null;
    //private DcMotor LinearSlide = null;
    //private Servo   SlideClaw   = null;
    //uncomment servo and linarslide variables when in use, also all other mentions

    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        RightFront  = hardwareMap.get(DcMotor.class, "RightFront");
        LeftFront   = hardwareMap.get(DcMotor.class, "LeftFront");
        LeftBack    = hardwareMap.get(DcMotor.class, "LeftBack");
        RightBack   = hardwareMap.get(DcMotor.class, "RightBack");
        //LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        //SlideClaw   = hardwareMap.get(Servo.class,   "SlideClaw");


        //LeftFront.setDirection(DcMotor.Direction.REVERSE);
        //LeftBack.setDirection(DcMotor.Direction.REVERSE);
        //RightFront.setDirection(DcMotor.Direction.REVERSE);
        //RightBack.setDirection(DcMotor.Direction.FORWARD);
        
    }

    @Override
    public void loop() {

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        // keep 1.1  on the x to not mess up the <speeds> array below
        double y  = -gamepad1.left_stick_y;
        double x  = gamepad1.left_stick_x*1.1;
        double rx  = gamepad1.right_stick_x;
        //Claww.setPosition(0);
        

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
        double[] speeds = {
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

        LeftFront.setPower(speeds[0]);
        LeftBack.setPower(speeds[1]);
        RightFront.setPower(speeds[2]);
        RightBack.setPower(speeds[3]);
        
        double spinpower = gamepad1.right_stick_x;

        //if(gamepad2.left_trigger >= 1){
           //LinearSlide.setPower(50);
        //}
        /*else if (gamepad2.right_trigger >=1){
            LinearSlide.setPower(-50);
        }  */
        /*
        //if (gamepad2.x){
            //LinearSlide.setPower(0);
            
        }*/
        /*
        if (gamepad2.b == true){
            SlideClaw.setPosition(0.9);
        }
        if(gamepad2.a == true){
            SlideClaw.setPosition(0.1);
        }
        */
        
        
        
        
        
        
        
        
   
        
       
    }
}
