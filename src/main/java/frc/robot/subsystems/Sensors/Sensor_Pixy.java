package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class Sensor_Pixy extends SubsystemBase {
    
    // Setup Pixy Cam
    private Pixy2 pixycam;
    boolean isCamera = false;
    int state = -1 ;

    ArrayList<Block> redBalls, blueBalls;


    public Sensor_Pixy() {
        pixycam = Pixy2.createInstance(Pixy2.LinkType.SPI);
        state = pixycam.init();
        pixycam.setLamp((byte) 0, (byte) 0); // Turns the LEDs off
        isCamera = state >= 0 ;
        dashboardOut();
    }
    
    

    @Override
    public void periodic() {
        if (!isCamera){
            state = pixycam.init(); // if no camera present, try to initialize
        }
        isCamera = state >= 0 ;
        pixycam.getCCC().getBlocks( false , Pixy2CCC.CCC_SIG1 , 25 ); //run getBlocks with arguments to have the camera
        redBalls = pixycam.getCCC().getBlockCache();
        pixycam.getCCC().getBlocks( false , Pixy2CCC.CCC_SIG5 , 25 ); //run getBlocks with arguments to have the camera
        blueBalls = pixycam.getCCC().getBlockCache();
        // if (redBalls.size() > 0 ) {
        //     double xcoord = redBalls.get( 0 ).getX(); // x position of the largest target
        //     double ycoord = redBalls.get( 0 ).getY(); // y position of the largest target
        //     String data = redBalls.get( 0 ).toString(); // string containing target info
        //     SmartDashboard.putBoolean( "present" , true ); // show there is a target present
        //     SmartDashboard.putNumber( "Xccord" ,xcoord);
        //     SmartDashboard.putNumber( "Ycoord" , ycoord);
        //     SmartDashboard.putString( "Data" , data );
        //     SmartDashboard.putNumber( "Number of Targets" , redBalls.size()); //push to dashboard how many targets are detected
        // } else {
        //     SmartDashboard.putBoolean( "present" , false );
        //     SmartDashboard.putNumber( "Number of Targets" , redBalls.size()); //push to dashboard how many targets are detected
        // }

        dashboardOut();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void dashboardOut() {
        SmartDashboard.putBoolean( "Camera" , isCamera); //publish if we are connected
        SmartDashboard.putNumber( "Red Targets" , redBalls.size()); //push to dashboard how many targets are detected
        SmartDashboard.putNumber( "Blue Targets" , blueBalls.size()); //push to dashboard how many targets are detected

    }
}
