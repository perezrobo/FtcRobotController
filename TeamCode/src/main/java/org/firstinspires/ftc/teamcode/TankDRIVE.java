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
@TeleOp(name="TANK DRIVE", group="Iterative Opmode")
public class TankDRIVE extends OpMode {

    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */

    // declare and initialize four DcMotors.
    private DcMotor FrontLM  = null;
    private DcMotor FrontRM = null;
    private DcMotor BackLM   = null;
    private DcMotor BackRM  = null;
    private DcMotor Arm = null;
    private Servo Floor = null;
    //private DcMotor DuckSpinner;
    //private DcMotor Claw;
    

    @Override
    public void init() {

        // Name strings must match up with the config on the Robot Controller
        // app.
        FrontLM   = hardwareMap.get(DcMotor.class, "FrontLM");
        FrontRM  = hardwareMap.get(DcMotor.class, "FrontRM");
        BackLM    = hardwareMap.get(DcMotor.class, "BackLM");
        BackRM   = hardwareMap.get(DcMotor.class, "BackRM");
        Arm = hardwareMap.get(DcMotor.class, "Arm");
        Floor = hardwareMap.get(Servo.class, "Floor");
        
        //DuckSpinner   = hardwareMap.get(DcMotor.class, "DuckSpinner");
        //Claw   = hardwareMap.get(DcMotor.class, "Claw");
        
    }
    
    

    @Override
    public void loop() {
        
        

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        double rly   = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double y  = -gamepad1.right_stick_x;
        double rry = gamepad1.right_stick_y;
//http://192.168.43.1:8080/java/editor.html?/src/org/firstinspires/ftc/teamcode/MecanumDrive.java
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
        //double[] speeds = {
          //  (y + x + rx),
            //(y - x + rx),
            //(y - x - rx),
            //(y + x - rx)
        //};
        // Because we are adding vectors and motors only take values between
        // [-1,1] we may need to normalize them.

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        //double max = Math.abs(speeds[0]);
        //for(int i = 0; i < speeds.length; i++) {
        //    if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        //}

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        //if (max > 1) {
        //    for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        //}

        // apply the calculated values to the motors.
        //FrontLM.setPower(speeds[1]);
        //FrontRM.setPower(speeds[2]);
        //BackLM.setPower(speeds[0]);
        //BackRM.setPower(speeds[3]);
        
        if(rly >= .1 || rly <= -.1){
            FrontLM.setPower(rly);
            BackLM.setPower(rly);
        }
        if(rry >= .1 || rry <= .1 ){
            FrontRM.setPower(rry);
            BackRM.setPower(rry);
        }
        
        if (gamepad1.x == true){
            Floor.setPosition(0.98);
        }
        if (gamepad1.y == true){
            Floor.setPosition(0.01);
        }   
        if (gamepad1.right_trigger >= .1 || gamepad1.left_trigger >= .1 ){
            Arm.setPower(gamepad1.right_trigger);
            Arm.setPower(-gamepad1.left_trigger);
        
        }
        
       /* if (gamepad1.y == true){
            DuckSpinner.setPower(-100);
            
        }
        
        
        if (gamepad1.x == true){
            DuckSpinner.setPower(100);
            
        }
        if (gamepad1.b == true){
            DuckSpinner.setPower(0);
            
        }*/
        
        
        
    }
}