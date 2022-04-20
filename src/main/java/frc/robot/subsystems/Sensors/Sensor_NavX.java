package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModeConstants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensor_NavX extends SubsystemBase {

    // gyro 
    AHRS gyroDrive;
    PIDController turnController;

    public Sensor_NavX() {
        // initialize the gyro
        try {
        /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
        /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
        /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            gyroDrive = new AHRS(SPI.Port.kMXP); 
         } catch (RuntimeException ex ) {
              DriverStation.reportError("Drivetrain.java...Error instantiating navX-MXP:  " + ex.getMessage(), true);
         }
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
        if (ModeConstants.navxDebug)
        {
            SmartDashboard.putBoolean("IMU_Connected", gyroDrive.isConnected());
            SmartDashboard.putNumber("IMU Yaw", gyroDrive.getYaw());
            SmartDashboard.putNumber("IMU TEXT", gyroDrive.getYaw());
        }
    }

    public double getYaw() {
        return gyroDrive.getYaw();
      }
    
        /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(gyroDrive.getAngle(), 360);
    }
    
    public void resetHeading() {
    gyroDrive.zeroYaw();
    }
}
