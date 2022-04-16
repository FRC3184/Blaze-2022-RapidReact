package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModeConstants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensor_ODS extends SubsystemBase {

    AnalogInput sensor_ods;

    public Sensor_ODS(int portNum) {
        sensor_ods = new AnalogInput(portNum);
        dashboardOut();
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

    public double getODSVal() {
        return sensor_ods.getValue();
    }

    public void dashboardOut() {
        if (ModeConstants.odsDebug) {
            SmartDashboard.putNumber("ODS val", sensor_ods.getValue());
        }
    }
}
