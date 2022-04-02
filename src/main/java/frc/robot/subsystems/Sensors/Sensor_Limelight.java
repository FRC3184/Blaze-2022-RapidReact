package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensor_Limelight extends SubsystemBase {

    NetworkTable limeTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv, tx, ty, ta, ts;
    double tsVal, txVal;
    boolean tvVal;

    double skewTarget = -45.0;
    double skewDeadzone = 5.0;

    public Sensor_Limelight() {

        dashboardOut();
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

    public void turnOnLEDs() {
        limeTable.getEntry("ledMode").setNumber(3);
    }

    public void turnOffLEDs() {
        limeTable.getEntry("ledMode").setNumber(1);
    }

    public void pipelineLEDs() {
        limeTable.getEntry("ledMode").setNumber(0);
    }

    public double getSkew () {
        ts = limeTable.getEntry("ts");
        tsVal = ts.getDouble(100);
        return tsVal;
    }

    public double getRotate () {
        tx = limeTable.getEntry("tx");
        txVal = tx.getDouble(100);
        return txVal;
    }

    public boolean hasValidTarget() {
        double tvDouble;
        tv = limeTable.getEntry("tv");
        tvDouble = tv.getDouble(-1);
        if (tvDouble == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSkewed() {
        ts = limeTable.getEntry("ts");
        tsVal = ts.getDouble(-1);
        if (tsVal > skewTarget - skewDeadzone && tsVal < skewTarget + skewDeadzone ) {
            return true;
        } else {
            return false;
        }
    }
    public double getDistFromFender() {
        double limeAngle = limeTable.getEntry("ty").getDouble(100);
        return (0.0084 * Math.pow(limeAngle, 2)) - (0.3072 * limeAngle) + (3.4474);
    }

    // public double getTargetDist() {
    //     double highGoalHeight           = 104.0; // inches
    //     double limelightHeightFromFloor = 38.0;  // inches
    //     double limelightAngleFromFloor  = Math.toRadians(20.0); // radians
    //     double limelightTargetAngle = Math.toRadians(limeTable.getEntry("ty").getDouble(100));

    //     return (highGoalHeight - limelightHeightFromFloor) / Math.tan(limelightAngleFromFloor + limelightTargetAngle);
    // }

    public void dashboardOut() {
        SmartDashboard.putNumber("limelightskew", tsVal);
        SmartDashboard.putNumber("limelight angle", limeTable.getEntry("tx").getDouble(100));
        SmartDashboard.putNumber("limelight angle horizontal", limeTable.getEntry("ty").getDouble(100));
        SmartDashboard.putNumber("Distance to Target", getDistFromFender());
        SmartDashboard.putNumber("Limelight LED Mode", limeTable.getEntry("ledMode").getDouble(100));
        SmartDashboard.putNumber("Valid Target", limeTable.getEntry("tv").getDouble(-1));
    }
}
