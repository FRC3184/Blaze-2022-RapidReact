package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeArm extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeArm = new CANSparkMax(IntakeConstants.kIntakeArmMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_IntakeRoller = new CANSparkMax(IntakeConstants.kIntakeRollerMotorPort, MotorType.kBrushless);
    //private final RelativeEncoder m_IntakeArmEnc = m_IntakeArm.getEncoder();
    //private final RelativeEncoder m_IntakeRollerEnc = m_IntakeRoller.getEncoder();

    public IntakeArm() {
        m_IntakeRoller.setInverted(IntakeConstants.intakeRollerInverted);
        m_IntakeArm.setInverted(IntakeConstants.intakeArmInverted);
    }
    
    

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void runIntakeRoller(double rollerSpeed) {
        m_IntakeRoller.set(rollerSpeed);
    }

    public void retractIntake(double speed) {
        m_IntakeArm.set(speed);
    }

    public void deployIntake(double speed) {
        m_IntakeArm.set(-speed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        
    }

    public void dashboardOut() {
        
    }
}
