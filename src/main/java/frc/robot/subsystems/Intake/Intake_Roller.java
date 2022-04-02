package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ModeConstants;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake_Roller extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeRoller = new CANSparkMax(IntakeConstants.kIntakeRollerMotorPort, MotorType.kBrushless);

    public Intake_Roller() {
        m_IntakeRoller.setInverted(IntakeConstants.intakeRollerInverted);
    }
    
    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void intake (double rollerSpeed) {
        m_IntakeRoller.set(rollerSpeed);
    }

    public void outtake (double rollerSpeed) {
        m_IntakeRoller.set(-rollerSpeed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        
    }

    public void dashboardOut() {
        if (ModeConstants.intakeRollerDebug) {
            
        }
        
    }
}
