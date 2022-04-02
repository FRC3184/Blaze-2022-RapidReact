package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants.ModeConstants;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter_Hood extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_hood = new CANSparkMax(ShooterConstants.kHoodMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_hoodEncoder = m_hood.getEncoder();
    
    DigitalInput hoodUpLimit = new DigitalInput(ShooterConstants.hoodUpLimitPort);
    DigitalInput hoodDownLimit = new DigitalInput(ShooterConstants.hoodDownLimitPort);

    // Shooter PID variables 
    private SparkMaxPIDController m_hoodPID;
    private double kP, kI, kD, kIz, kFF; 
    private double kMaxOut, kMinOut, maxRPM;


    public Shooter_Hood() {
        m_hood.setInverted(ShooterConstants.hoodInverted);

        m_hoodEncoder.setVelocityConversionFactor(1.0);

        resetEncoders();
        
        // ** SETTING UP PID FOR SHOOTER
        // PID init
        m_hoodPID = m_hood.getPIDController();
        // PID coefficients
        kP = 6e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 1.0/5700.0; 
        kMaxOut = 1; 
        kMinOut = -1;
        maxRPM = 5700;

        // set PID coefficients
        m_hoodPID.setP(kP);
        m_hoodPID.setI(kI);
        m_hoodPID.setD(kD);
        m_hoodPID.setIZone(kIz);
        m_hoodPID.setFF(kFF);
        m_hoodPID.setOutputRange(kMinOut, kMaxOut);

        //dashboardOut();
    }
    
    @Override
    public void periodic() {
        dashboardOut();
    }

    @Override
    public void simulationPeriodic() {

    }

    public void runHood(double RPM) {
        double hoodSetpoint = RPM;
        if (RPM < 0 && hoodDownLimit.get()){
            m_hoodPID.setReference(hoodSetpoint, CANSparkMax.ControlType.kVelocity);
        } else if (RPM > 0 && hoodUpLimit.get()) {
            m_hoodPID.setReference(hoodSetpoint, CANSparkMax.ControlType.kVelocity);
        } else {
            m_hoodPID.setReference(0, CANSparkMax.ControlType.kVelocity);
        }

        if (!hoodDownLimit.get()) {
            m_hoodEncoder.setPosition(0);
        }
    }

    public double getHoodEnc () {
        return m_hoodEncoder.getPosition();
    }

    public void stopHood() {
        m_hoodPID.setReference(0, CANSparkMax.ControlType.kVelocity);
    }

    public boolean getHoodDownLimit () {
        return hoodDownLimit.get();
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_hoodEncoder.setPosition(0);
    }

    public void dashboardOut() {
        if (ModeConstants.shootHoodDebug) {
            SmartDashboard.putNumber("Hood Enc", m_hoodEncoder.getPosition());
            SmartDashboard.putNumber("Hood Vel", m_hoodEncoder.getVelocity());
            SmartDashboard.putBoolean("Hood Up Limit", hoodUpLimit.get());
            SmartDashboard.putBoolean("Hood Down Limit", hoodDownLimit.get());
        }
    }
}
