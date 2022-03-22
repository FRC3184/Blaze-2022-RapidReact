package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensor_ODS extends SubsystemBase {

    AnalogInput sensor_ods;

    public Sensor_ODS() {
        sensor_ods = new AnalogInput(ShooterConstants.highODSPort);


        //dashboardOut();
    }
    
    

    @Override
    public void periodic() {
        dashboardOut();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void getLimelightValues() {

    }

    public void dashboardOut() {
        SmartDashboard.putNumber("ODS val", sensor_ods.getValue());

    }
}
