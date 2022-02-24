package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeArm extends SubsystemBase {
    
    // Setup motors and Encoders
    private final CANSparkMax m_IntakeRoller = new CANSparkMax(IntakeConstants.kIntakeRollerMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_IntakePivot = new CANSparkMax(IntakeConstants.kIntakePivotMotorPort, MotorType.kBrushless);

    public IntakeArm() {
        m_IntakeRoller.setInverted(IntakeConstants.intakeRollerInverted);
        m_IntakePivot.setInverted(IntakeConstants.intakeRollerInverted);
    }
    
    

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void PivotUp(double Speed){
        m_IntakePivot.set(Speed);
    }

    public void PivotDown(double Speed){
        m_IntakePivot.set(-Speed);
    }

    public void intake(double rollerSpeed) {
        m_IntakeRoller.set(rollerSpeed);
    }

    public void outake(double rollerSpeed) {
        m_IntakeRoller.set(-rollerSpeed);
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        
    }

    public void dashboardOut() {
        
    }
}
