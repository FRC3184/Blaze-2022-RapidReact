package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_Front_ShooterW = new CANSparkMax(ShooterConstants.kFrontShooterMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_Back_ShooterW = new CANSparkMax(ShooterConstants.kBackShooterMotorPort, MotorType.kBrushless);

    private final RelativeEncoder m_frontShooterEncoder = m_Front_ShooterW.getEncoder();
    private final RelativeEncoder m_backShooterkEncoder = m_Back_ShooterW.getEncoder();

    public Shooter() {
        m_Front_ShooterW.setInverted(ShooterConstants.frontShooterInverted);
        m_Back_ShooterW.setInverted(ShooterConstants.backShooterInverted);

        m_frontShooterEncoder.setVelocityConversionFactor(1.0);
        m_backShooterkEncoder.setVelocityConversionFactor(1.0);

        resetEncoders();

        dashboardOut();
    }
    
    

    @Override
    public void periodic() {
    dashboardOut();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void shoot(double frontSpeed, double backSpeed) {
        m_Front_ShooterW.set(frontSpeed);
        m_Back_ShooterW.set(backSpeed);
      }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_frontShooterEncoder.setPosition(0);
        m_backShooterkEncoder.setPosition(0);
    }

    public double inchToClicks(double inches) {
        double clicks;

        clicks = inches * 10;

        return clicks;
    }


    public void dashboardOut() {
        SmartDashboard.putNumber("Front Shoot Enc", m_frontShooterEncoder.getPosition());
        SmartDashboard.putNumber("Back Shoot Enc", m_backShooterkEncoder.getPosition());

        SmartDashboard.putNumber("Front Shoot Vel", m_frontShooterEncoder.getVelocity());
        SmartDashboard.putNumber("Back Shoot Vel", m_backShooterkEncoder.getVelocity());
    }
}
