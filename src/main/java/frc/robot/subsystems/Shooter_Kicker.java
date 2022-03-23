package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter_Kicker extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_KickerW = new CANSparkMax(ShooterConstants.kKickerMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_kickerEncoder = m_KickerW.getEncoder();

    // Shooter PID variables 
    private SparkMaxPIDController m_kickPID;
    public double kP, kI, kD, kIz, kFF; 
    public double kMaxOut, kMinOut, maxRPM;

    public Shooter_Kicker() {
        m_KickerW.setInverted(ShooterConstants.kickerInverted);

        m_kickerEncoder.setVelocityConversionFactor(1.0);

        resetEncoders();
        
        // ** SETTING UP PID FOR SHOOTER
        // PID init
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

    public void runKicker(double RPM) {
        double kickSetpoint = RPM;
        m_kickPID.setReference(kickSetpoint, CANSparkMax.ControlType.kVelocity);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {

    }

    public double inchToClicks(double inches) {
        double clicks;
        clicks = inches * 10;
        return clicks;
    }


    public void dashboardOut() {

    }
}
