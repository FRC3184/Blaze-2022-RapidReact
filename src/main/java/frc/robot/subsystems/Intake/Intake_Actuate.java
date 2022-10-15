package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ModeConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake_Actuate extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeArmLeft = new CANSparkMax(IntakeConstants.kLeftIntakeArmMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_IntakeArmRight = new CANSparkMax(IntakeConstants.kRightIntakeArmMotorPort, MotorType.kBrushless);
    private final RelativeEncoder m_IntakeArmLeftEnc = m_IntakeArmLeft.getEncoder();
    private final RelativeEncoder m_IntakeArmRightEnc = m_IntakeArmRight.getEncoder();

    DigitalInput intakeActuateUpLeftLimit = new DigitalInput(IntakeConstants.intakeActuateUpLeftLimitPort);;
    DigitalInput intakeActuateUpRightLimit = new DigitalInput(IntakeConstants.intakeActuateUpRightLimitPort);
    DigitalInput intakeActuateDownLeftLimit = new DigitalInput(IntakeConstants.intakeActuateDownLeftLimitPort);
    DigitalInput intakeActuateDownRightLimit = new DigitalInput(IntakeConstants.intakeActuateDownRightLimitPort);

    public Intake_Actuate() {
        m_IntakeArmLeft.setInverted(IntakeConstants.intakeLeftArmInverted);
        m_IntakeArmRight.setInverted(IntakeConstants.intakeRightArmInverted);
        dashboardOut();
    }
    
    

    @Override
    public void periodic() {
        dashboardOut();

    }

    @Override
    public void simulationPeriodic() {

    }

    public void retractIntake(double speed) {
        if (Math.abs(speed) > 0.1)
        {
            speed = 0.1;
        }

        if (intakeActuateUpLeftLimit.get() && intakeActuateUpRightLimit.get()) {
            m_IntakeArmLeft.set(speed);
            m_IntakeArmRight.set(speed);
        } else {
            m_IntakeArmLeft.set(0);
            m_IntakeArmRight.set(0);
        }
    }

    public void deployIntake(double speed) {
        if (Math.abs(speed) > 0.1)
        {
            speed = 0.1;
        }

        if (intakeActuateDownLeftLimit.get() || intakeActuateDownRightLimit.get()) {
            m_IntakeArmLeft.set(-speed);
            m_IntakeArmRight.set(-speed);
        } else {
            m_IntakeArmLeft.set(0);
            m_IntakeArmRight.set(0);
        }
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_IntakeArmRightEnc.setPosition(0);
        m_IntakeArmLeftEnc.setPosition(0);
        
    }

    public void dashboardOut() {
        if (ModeConstants.intakeActuateDebug) {
            SmartDashboard.putBoolean("intake down left", intakeActuateDownLeftLimit.get());
            SmartDashboard.putBoolean("intake down right", intakeActuateDownRightLimit.get());
            SmartDashboard.putBoolean("intake up left", intakeActuateUpLeftLimit.get());
            SmartDashboard.putBoolean("intake up right", intakeActuateUpRightLimit.get());
        }
    }
}
