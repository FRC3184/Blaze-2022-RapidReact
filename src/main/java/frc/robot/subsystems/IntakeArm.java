package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class IntakeArm extends SubsystemBase {
    
    // Setup motors and Encoders
    private final VictorSPX m_IntakeRoller = new VictorSPX(IntakeConstants.kIntakeRollerMotorPort);

    public IntakeArm() {
        m_IntakeRoller.setInverted(IntakeConstants.intakeRollerInverted);
    }
    
    

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void intake(double rollerSpeed) {
        m_IntakeRoller.set(VictorSPXControlMode.PercentOutput, rollerSpeed);
    }

    public void outake(double rollerSpeed) {
        m_IntakeRoller.set(VictorSPXControlMode.PercentOutput, -rollerSpeed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        
    }

    public void dashboardOut() {
        
    }
}
