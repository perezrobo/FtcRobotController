package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.WebcamConfiguration;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvWebcam;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.util.List;
import java.lang.String;


@Autonomous




public class VisionPortals extends LinearOpMode{
   
    public   OpenCvWebcam Webcam1 = null;
   
    
     @Override
    public void runOpMode() {
    WebcamName camera = hardwareMap.get(WebcamName.class, "Webcam1");
    
    
    
    TfodProcessor tfodProcessor = TfodProcessor.easyCreateWithDefaults();
    VisionPortal visionPortal1 = VisionPortal.easyCreateWithDefaults(camera, tfodProcessor);
    
    List<Recognition> recognitions = tfodProcessor.getRecognitions();
    for (Recognition recognition : recognitions)
    {
        String label = recognition.getLabel();
        float confidence = recognition.getConfidence();
        telemetry.addLine("Recognized object:" + label);
        telemetry.addLine("Confidence:" + confidence);
    }
    
    AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
    VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(camera, aprilTagProcessor);
    
    List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
    for (AprilTagDetection detection : detections)
    {
     int id = detection.id;
     AprilTagPoseFtc tagPose = detection.ftcPose;
     
     
     telemetry.addLine("Detected tag ID:" + id);
     telemetry.addLine("Distance to tag:" + tagPose.range);
     telemetry.addLine("Bearing to tag:"  + tagPose.bearing);
     telemetry.addLine("Angle of tag:"    + tagPose.yaw);
     
    double   range    =   detection.ftcPose.range;
    double   bearing  =   detection.ftcPose.bearing;
    double   yaw      =   detection.ftcPose.yaw;
   
    }

}



}



