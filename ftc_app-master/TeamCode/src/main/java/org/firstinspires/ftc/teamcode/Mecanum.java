package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class Mecanum extends LinearOpMode {
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor collector = null;
    private DcMotor rampa = null;

    private DigitalChannel limitasus = null;
    private DigitalChannel limitajos = null;
    @Override
    public void runOpMode()
    {
        frontLeft = hardwareMap.get(DcMotor.class , "frontleft");
        frontRight = hardwareMap.get(DcMotor.class , "frontright");
        backLeft = hardwareMap.get(DcMotor.class ,  "backleft");
        backRight = hardwareMap.get(DcMotor.class , "backright");
        collector = hardwareMap.get(DcMotor.class , "collector");

        rampa = hardwareMap.get(DcMotor.class ,"rampa");
        limitajos = hardwareMap.get(DigitalChannel.class , "limitajos");
        limitasus = hardwareMap.get(DigitalChannel.class , "limitasus");
        int stare = 0;
        int k=5;
        while(opModeIsActive() && !isStopRequested())
        {
            if(this.gamepad2.left_stick_y !=0)
            {
                collector.setPower(-this.gamepad2.left_stick_y);
            }
            if(this.gamepad1.a == true)
            {
                if(limitasus.getState() == true && limitajos.getState() == false) {stare=1; k=6;}
                if(limitajos.getState() == true && limitasus.getState() == false) {stare=0; k=5;}
                if(stare == 1) rampa.setPower(-0.75);
                if(stare == 0) rampa.setPower(0.75);
                if(limitajos.getState() == false && limitasus.getState() == false)
                {
                    stare=3;;
                }

            }
            if(stare == 3)
            {
                if(limitasus.getState() == true) {rampa.setPower(0.0);stare = 1;}
                if(limitajos.getState() == true) {rampa.setPower(0.0); stare = 0;}
                if(k==5) rampa.setPower(0.75);
                if(k==6) rampa.setPower(-0.75);

            }
            //stop
            if(this.gamepad1.left_stick_x==0 && this.gamepad1.left_stick_y==0)
            {
                telemetry.addData("Motor", "Stop");
                frontRight.setPower(0.0);
                frontLeft.setPower(0.0);
                backRight.setPower(0.0);
                backLeft.setPower(0.0);
            }
            //fata-spate
            if(this.gamepad1.left_stick_x==0 && this.gamepad1.left_stick_y!=0)
            {
                telemetry.addData("Motor","Fata/Spate");
                frontRight.setPower(-this.gamepad1.left_stick_y);
                frontLeft.setPower(-this.gamepad1.left_stick_y);
                backRight.setPower(-this.gamepad1.left_stick_y);
                backLeft.setPower(-this.gamepad1.left_stick_y);
            }
            //stanga dreapta
            if(this.gamepad1.left_stick_x!=0 && this.gamepad1.left_stick_y==0)
            {
                telemetry.addData("Motor" , "Stanga/Dreapta");
                frontRight.setPower(-this.gamepad1.left_stick_x);
                frontLeft.setPower(this.gamepad1.left_stick_x);
                backRight.setPower(this.gamepad1.left_stick_x);
                backLeft.setPower(-this.gamepad1.left_stick_x);
            }
            // diagonale
            /*if(this.gamepad1.left_stick_x>0 && this.gamepad1.left_stick_y<0)
            {
                frontLeft.setPower(0.75);
                backRight.setPower(0.75);
            }
            if(this.gamepad1.left_stick_x<0 && this.gamepad1.left_stick_y>0)
            {
                frontLeft.setPower(-0.75);
                backRight.setPower(-0.75);
            }
            if(this.gamepad1.left_stick_x<0 && this.gamepad1.left_stick_y<0)
            {
                frontRight.setPower(0.75);
                backLeft.setPower(0.75);
            }
            if(this.gamepad1.left_stick_x>0 && this.gamepad1.left_stick_y>0)
            {
                frontRight.setPower(-0.75);
                backLeft.setPower(-0.75);
            }*/
            //intoarcere stanga/dreapta
            if(this.gamepad1.right_stick_x!=0 && this.gamepad1.right_stick_y==0)
            {
                telemetry.addData("Motor","Rotatie");
                frontRight.setPower(-this.gamepad1.right_stick_x);
                frontLeft.setPower(this.gamepad1.right_stick_x);
                backRight.setPower(-this.gamepad1.right_stick_x);
                backLeft.setPower(this.gamepad1.right_stick_x);
            }
        }
    }
}
