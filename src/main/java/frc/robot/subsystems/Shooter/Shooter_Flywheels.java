package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants.ModeConstants;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter_Flywheels extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_Back_ShooterW = new CANSparkMax(ShooterConstants.kBackShooterMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_Front_ShooterW = new CANSparkMax(ShooterConstants.kFrontShooterMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_backShooterEncoder = m_Back_ShooterW.getEncoder();
    private final RelativeEncoder m_frontShooterkEncoder = m_Front_ShooterW.getEncoder();

    // Shooter PID variables 
    private SparkMaxPIDController m_frontShootPID, m_backShootPID;
    public double kP, kI, kD, kIz, kFF; 
    public double kMaxOut, kMinOut, maxRPM;
    private double frontSpeed, backSpeed;


    public Shooter_Flywheels() {
        m_Back_ShooterW.setInverted(ShooterConstants.backShooterInverted);
        m_Front_ShooterW.setInverted(ShooterConstants.frontShooterInverted);

        m_backShooterEncoder.setVelocityConversionFactor(1.0);
        m_frontShooterkEncoder.setVelocityConversionFactor(1.0);

        resetEncoders();

        frontSpeed = 2200;
        backSpeed = 2200;
        
        // ** SETTING UP PID FOR SHOOTER
        // PID init
        m_frontShootPID = m_Front_ShooterW.getPIDController();
        m_backShootPID = m_Back_ShooterW.getPIDController();
        // PID coefficients
        kP = 10e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 1.0 / 5500.0; 
        kMaxOut = 1; 
        kMinOut = -1;
        maxRPM = 5800;

        // set PID coefficients
        m_frontShootPID.setP(kP);
        m_frontShootPID.setI(kI);
        m_frontShootPID.setD(kD);
        m_frontShootPID.setIZone(kIz);
        m_frontShootPID.setFF(kFF);
        m_frontShootPID.setOutputRange(kMinOut, kMaxOut);

        m_backShootPID.setP(kP);
        m_backShootPID.setI(kI);
        m_backShootPID.setD(kD);
        m_backShootPID.setIZone(kIz);
        m_backShootPID.setFF(kFF);
        m_backShootPID.setOutputRange(kMinOut, kMaxOut);

        dashboardOut();
    }
    
    @Override
    public void periodic() {
        dashboardOut();
    }

    @Override
    public void simulationPeriodic() {

    }

    public void runShooter() {
        double frontSetpoint = frontSpeed;
        double backSetpoint = backSpeed;

        m_frontShootPID.setReference(frontSetpoint, CANSparkMax.ControlType.kVelocity);
        m_backShootPID.setReference(backSetpoint, CANSparkMax.ControlType.kVelocity);
    }

    public void stopShooter() {
        m_frontShootPID.setReference(0, CANSparkMax.ControlType.kVelocity);
        m_backShootPID.setReference(0, CANSparkMax.ControlType.kVelocity);
    }

    public double[] getShotSpeed() {
        double[] speeds =  {frontSpeed, backSpeed};
        return speeds;
    }

    public void setShotSpeed(double speed) {
        frontSpeed = speed * 2.0;
        backSpeed = speed;

        if (frontSpeed > 7000) {
            frontSpeed = 7000;
            backSpeed = frontSpeed / 2.0;
        }
    }

    public void setShotSpeed(double speedF, double speedB) {
        frontSpeed = speedF;
        backSpeed = speedB;

        if (frontSpeed > 7000) {
            frontSpeed = 7000;
        }
        if (backSpeed > 7000) {
            backSpeed = 7000;
        }
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_backShooterEncoder.setPosition(0);
        m_frontShooterkEncoder.setPosition(0);
    }

    public double[] getFlyWheelSpeed () { 
        double[] flywheelSpeeds = {m_frontShooterkEncoder.getVelocity(), m_backShooterEncoder.getVelocity()};
        return flywheelSpeeds;
    }

    private double flySpeedDZ = 100;

    public boolean flywheelUpToSpeed () {
        double[] flySpeed = getFlyWheelSpeed(); 
        double[] refSpeed = getShotSpeed();

        if (flySpeed[1] == 0)
        {
            return false;
        } else {
            return (flySpeed[0] >= (refSpeed[0] - flySpeedDZ) && flySpeed[1] >= (refSpeed[1]-flySpeedDZ));
        }
      }


    public void dashboardOut() {
        if (ModeConstants.shootFlywheelDebug) {
            SmartDashboard.putNumber("Big Wheel Enc", m_backShooterEncoder.getPosition());
            SmartDashboard.putNumber("Little Wheel Enc", m_frontShooterkEncoder.getPosition());

            SmartDashboard.putNumber("Big Shoot Vel", m_backShooterEncoder.getVelocity());
            SmartDashboard.putNumber("Little Shoot Vel", m_frontShooterkEncoder.getVelocity());

            SmartDashboard.putBoolean("Shooter upToSpeed", flywheelUpToSpeed());
        }
    }
}
