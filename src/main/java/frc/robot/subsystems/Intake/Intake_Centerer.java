package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ModeConstants;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake_Centerer extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeCentererLeft = new CANSparkMax(IntakeConstants.kCentererLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_IntakeCentererRight = new CANSparkMax(IntakeConstants.kCentererRightMotorPort, MotorType.kBrushless);

    public Intake_Centerer() {
        m_IntakeCentererLeft.setInverted(IntakeConstants.intakeCentererLeftInverted);
        m_IntakeCentererRight.setInverted(IntakeConstants.intakeCentererRightInverted);
    }
    
    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void intake (double speed) {
        m_IntakeCentererLeft.set(speed);
        m_IntakeCentererRight.set(speed);
    }

    public void outtake (double speed) {
        m_IntakeCentererLeft.set(-speed);
        m_IntakeCentererRight.set(-speed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        
    }

    public void dashboardOut() {
        if (ModeConstants.intakeCenterDebug) {

        }
    }
}
