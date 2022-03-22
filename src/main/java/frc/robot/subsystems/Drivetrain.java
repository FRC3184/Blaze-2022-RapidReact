/* Functions for the drivetrain subsystem */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
// import frc.robot.Constants.ModeConstants;
import frc.robot.Constants.ModeConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;

public class Drivetrain extends SubsystemBase {
  // Create instance of DriveConstants based on config

  // Setup motors
  private final CANSparkMax m_leftFrontMotor = new CANSparkMax(DriveConstants.kLeftFrontMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(DriveConstants.kLeftBackMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_rightFrontMotor = new CANSparkMax(DriveConstants.kRightFrontMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(DriveConstants.kRightBackMotorPort, MotorType.kBrushless);

  private final RelativeEncoder m_leftFrontEncoder = m_leftFrontMotor.getEncoder();
  private final RelativeEncoder m_leftBackEncoder = m_leftBackMotor.getEncoder();
  private final RelativeEncoder m_rightFrontEncoder = m_rightFrontMotor.getEncoder();
  private final RelativeEncoder m_rightBackEncoder = m_rightBackMotor.getEncoder();

  // The motors left/right side of the chassis
  private final MotorControllerGroup m_leftMotors;
  private final MotorControllerGroup m_rightMotors;

  // The robot's drive
  private final DifferentialDrive m_drive;

  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(
          DriveConstants.kLeftEncoderPorts[0],
          DriveConstants.kLeftEncoderPorts[1]);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(
          DriveConstants.kRightEncoderPorts[0],
          DriveConstants.kRightEncoderPorts[1]);

  // gyro 
  AHRS gyroDrive;
  PIDController turnController;

  /** Creates a new Drivetrain. */
  public Drivetrain() {

    m_leftMotors = new MotorControllerGroup(
      m_leftFrontMotor, m_leftBackMotor);

    m_rightMotors = new MotorControllerGroup(
      m_rightFrontMotor, m_rightBackMotor);

    m_leftFrontMotor.setInverted(DriveConstants.leftInverted);
    m_leftBackMotor.setInverted(DriveConstants.leftInverted);
    m_rightFrontMotor.setInverted(DriveConstants.rightInverted);
    m_rightBackMotor.setInverted(DriveConstants.rightInverted);

    //m_leftMotors.setInverted(isInverted);

    m_leftFrontEncoder.setVelocityConversionFactor(1.0);
    m_leftBackEncoder.setVelocityConversionFactor(1.0);
    m_rightFrontEncoder.setVelocityConversionFactor(1.0);
    m_rightBackEncoder.setVelocityConversionFactor(1.0);

    // initialize the gyro
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      gyroDrive = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Drivetrain.java...Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }

    resetEncoders();

    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
    dashboardOut();

  }

  @Override
  public void periodic() {
    dashboardOut();

  }

  @Override
  public void simulationPeriodic() {

  }

  public void drive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void aDrive(double xSpeed, double zRotation) {
    m_drive.arcadeDrive(xSpeed, zRotation);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    m_leftFrontEncoder.setPosition(0);
    m_leftBackEncoder.setPosition(0);
    m_rightFrontEncoder.setPosition(0);
    m_rightBackEncoder.setPosition(0);
  }

  public double inchToClicks(double inches) {
    double clicks;
    
    clicks = inches * 10;

    return clicks;
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    double leftAvg = (m_leftFrontEncoder.getPosition() + m_leftBackEncoder.getPosition())/2.0;
    double rightAvg = (m_rightFrontEncoder.getPosition() + m_rightBackEncoder.getPosition())/2.0;

    return (Math.abs(leftAvg) + Math.abs(rightAvg)) / 2.0;
  }

  public double getYaw() {
    return gyroDrive.getYaw();
  }

  public void resetHeading() {
    gyroDrive.zeroYaw();
  }

  public void dashboardOut() {
    // SmartDashboard.putNumber("Left Front Encoder", m_leftFrontEncoder.getPosition());
    // SmartDashboard.putNumber("Left Back Encoder", m_leftBackEncoder.getPosition());
    // SmartDashboard.putNumber("Right Front Encoder", m_rightFrontEncoder.getPosition());
    // SmartDashboard.putNumber("Right Back Encoder", m_rightBackEncoder.getPosition());

    // SmartDashboard.putNumber("Left Front Vel", m_leftFrontEncoder.getVelocity());
    // SmartDashboard.putNumber("Left Back Vel", m_leftBackEncoder.getVelocity());
    // SmartDashboard.putNumber("Right Front Vel", m_rightFrontEncoder.getVelocity());
    // SmartDashboard.putNumber("Right Back Vel", m_rightBackEncoder.getVelocity());

    if (ModeConstants.navxDebug)
    {
      SmartDashboard.putBoolean("IMU_Connected", gyroDrive.isConnected());
      SmartDashboard.putNumber("IMU Yaw", gyroDrive.getYaw());
    }
  }
}