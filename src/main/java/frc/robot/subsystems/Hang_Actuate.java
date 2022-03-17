package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants.*;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Hang_Actuate extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_ActuateL = new CANSparkMax(HangConstants.kActuateLeft, MotorType.kBrushless);
    private final CANSparkMax m_ActuateR = new CANSparkMax(HangConstants.kActuateRight, MotorType.kBrushless);

    private final RelativeEncoder m_actuateLEncoder = m_ActuateL.getEncoder();
    private final RelativeEncoder m_actuateREncoder = m_ActuateR.getEncoder();

    DigitalInput hangActuateLimit;

    public Hang_Actuate() {
        m_ActuateL.setInverted(HangConstants.actuateLeftInverted);
        m_ActuateR.setInverted(HangConstants.actuateRightInverted);

        m_actuateLEncoder.setVelocityConversionFactor(1.0);
        m_actuateREncoder.setVelocityConversionFactor(1.0);

        hangActuateLimit = new DigitalInput(HangConstants.hangActuateLimitPort);

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

    public void runActuatingArms(double ActuateSpeed) {
        m_ActuateL.set(ActuateSpeed);
        m_ActuateR.set(ActuateSpeed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_actuateLEncoder.setPosition(0);
        m_actuateREncoder.setPosition(0);
    }

    public boolean getHangAcutateLimit() {
        return hangActuateLimit.get();
    }

    public void dashboardOut() {
        SmartDashboard.putBoolean("HangActuateLimit", getHangAcutateLimit());
      }
}
