package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensor_Limelight extends SubsystemBase {

    NetworkTable limeTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx, ty, ta, ts;
    double tsVal;

    double skewTarget = -45.0;
    double skewDeadzone = 5.0;

    public Sensor_Limelight() {

        //dashboardOut();
    }
    
    

    @Override
    public void periodic() {
        dashboardOut();
        getLimelightValues();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void getLimelightValues() {
        tx = limeTable.getEntry("tx");
        ty = limeTable.getEntry("ty");
        ta = limeTable.getEntry("ta");
        ts = limeTable.getEntry("ts");
        tsVal = ts.getDouble(0.0);
    }

    public void turnOnLEDS() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledmode").setNumber(1);
    }

    public void turnOffLEDs() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledmode").setNumber(0);
    }

    public boolean skewedTarget() {
        ts = limeTable.getEntry("ts");
        tsVal = ts.getDouble(0.0);
        if (tsVal > skewTarget - skewDeadzone && tsVal < skewTarget + skewDeadzone ) {
            return true;
        } else {
            return false;
        }
    }

    public void dashboardOut() {
        SmartDashboard.putNumber("limelightskew", tsVal);

    }
}
