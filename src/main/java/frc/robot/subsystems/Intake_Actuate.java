package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake_Actuate extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeArmLeft = new CANSparkMax(IntakeConstants.kLeftIntakeArmMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_IntakeArmRight = new CANSparkMax(IntakeConstants.kRightIntakeArmMotorPort, MotorType.kBrushless);
    private final RelativeEncoder m_IntakeArmLeftEnc = m_IntakeArmLeft.getEncoder();
    private final RelativeEncoder m_IntakeArmRightEnc = m_IntakeArmRight.getEncoder();

    public Intake_Actuate() {
        m_IntakeArmLeft.setInverted(IntakeConstants.intakeLeftArmInverted);
        m_IntakeArmRight.setInverted(IntakeConstants.intakeRightArmInverted);
    }
    
    

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void retractIntake(double speed) {
        if (Math.abs(speed) > 0.1)
        {
            speed = 0.1;
        }
        m_IntakeArmLeft.set(speed);
        m_IntakeArmRight.set(speed);
    }

    public void deployIntake(double speed) {
        if (Math.abs(speed) > 0.1)
        {
            speed = 0.1;
        }
        m_IntakeArmLeft.set(-speed);
        m_IntakeArmRight.set(-speed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_IntakeArmRightEnc.setPosition(0);
        m_IntakeArmLeftEnc.setPosition(0);
        
    }

    public void dashboardOut() {
        
    }
}
