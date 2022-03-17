package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimeLight extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_High_ShooterW = new CANSparkMax(ShooterConstants.kHighShooterMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_Low_ShooterW = new CANSparkMax(ShooterConstants.kLowShooterMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_KickerW = new CANSparkMax(ShooterConstants.kKickerMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_highShooterEncoder = m_High_ShooterW.getEncoder();
    private final RelativeEncoder m_lowShooterkEncoder = m_Low_ShooterW.getEncoder();
    private final RelativeEncoder m_kickerEncoder = m_KickerW.getEncoder();

    // Shooter PID variables 
    private SparkMaxPIDController m_frontShootPID, m_backShootPID, m_kickPID;
    public double kP, kI, kD, kIz, kFF; 
    public double kMaxOut, kMinOut, maxRPM;
    private int shootSpeed;


    public LimeLight() {
        m_High_ShooterW.setInverted(ShooterConstants.highShooterInverted);
        m_Low_ShooterW.setInverted(ShooterConstants.lowShooterInverted);
        m_KickerW.setInverted(ShooterConstants.kickerInverted);

        m_highShooterEncoder.setVelocityConversionFactor(1.0);
        m_lowShooterkEncoder.setVelocityConversionFactor(1.0);
        m_kickerEncoder.setVelocityConversionFactor(1.0);

        resetEncoders();

        shootSpeed = 2200;
        
        // ** SETTING UP PID FOR SHOOTER
        // PID init
        m_frontShootPID = m_High_ShooterW.getPIDController();
        m_backShootPID = m_Low_ShooterW.getPIDController();
        m_kickPID = m_KickerW.getPIDController();
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

        m_kickPID.setP(kP);
        m_kickPID.setI(kI);
        m_kickPID.setD(kD);
        m_kickPID.setIZone(kIz);
        m_kickPID.setFF(kFF);
        m_kickPID.setOutputRange(kMinOut, kMaxOut);

        //dashboardOut();
    }
    
    

    @Override
    public void periodic() {
    //dashboardOut();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void runShooter() {
        double frontSetpoint = shootSpeed;
        double backSetpoint = shootSpeed;

        m_frontShootPID.setReference(frontSetpoint, CANSparkMax.ControlType.kVelocity);
        m_backShootPID.setReference(backSetpoint, CANSparkMax.ControlType.kVelocity);
    }

    public void runKicker(double RPM) {
        double kickSetpoint = RPM;
        m_kickPID.setReference(kickSetpoint, CANSparkMax.ControlType.kVelocity);
    }

    public int getShotSpeed() {
        return shootSpeed;
    }

    public void setShotSpeed(int speed) {
        shootSpeed = speed;
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_highShooterEncoder.setPosition(0);
        m_lowShooterkEncoder.setPosition(0);
    }

    public double inchToClicks(double inches) {
        double clicks;
        clicks = inches * 10;
        return clicks;
    }


    public void dashboardOut() {
        SmartDashboard.putNumber("Front Shoot Enc", m_highShooterEncoder.getPosition());
        SmartDashboard.putNumber("Back Shoot Enc", m_lowShooterkEncoder.getPosition());

        SmartDashboard.putNumber("Front Shoot Vel", m_highShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Back Shoot Vel", m_lowShooterkEncoder.getVelocity());
    }
}
