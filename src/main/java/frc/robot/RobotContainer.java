// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.driveModes.*;
import frc.robot.commands.independant.ActuateIn;
import frc.robot.commands.independant.ActuateOut;
import frc.robot.commands.independant.HoodDown;
import frc.robot.commands.independant.HoodUp;
import frc.robot.commands.independant.IntakeDeploy;
import frc.robot.commands.independant.IntakeRetract;
import frc.robot.commands.independant.Outtake;
import frc.robot.commands.independant.WinchIn;
import frc.robot.commands.independant.WinchOut;
import frc.robot.subsystems.Drive.Drivetrain;
import frc.robot.subsystems.Hang.Hang_Actuate;
import frc.robot.subsystems.Hang.Hang_Winch;
import frc.robot.subsystems.Intake.Intake_Actuate;
import frc.robot.subsystems.Intake.Intake_Centerer;
import frc.robot.subsystems.Intake.Intake_Roller;
import frc.robot.subsystems.Sensors.Sensor_Limelight;
import frc.robot.subsystems.Sensors.Sensor_NavX;
import frc.robot.subsystems.Sensors.Sensor_ODS;
import frc.robot.subsystems.Shooter.Shooter_Flywheels;
import frc.robot.subsystems.Shooter.Shooter_Hood;
import frc.robot.subsystems.Shooter.Shooter_Kicker;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final Common m_common = new Common();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter_Flywheels m_flywheel = new Shooter_Flywheels();
  private final Shooter_Kicker m_kicker = new Shooter_Kicker();
  private final Shooter_Hood m_hood = new Shooter_Hood();
  private final Hang_Actuate m_hangActuate = new Hang_Actuate();
  private final Hang_Winch m_hangWinch = new Hang_Winch();
  private final Intake_Actuate m_intakeAcutate = new Intake_Actuate();
  private final Intake_Roller m_intakeRoller = new Intake_Roller();
  private final Intake_Centerer m_intakeCenterer = new Intake_Centerer();
  private final Sensor_Limelight m_limelight = new Sensor_Limelight();
  private final Sensor_NavX m_navX = new Sensor_NavX();
  private final Sensor_ODS m_ODSHigh = new Sensor_ODS(ShooterConstants.highODSPort);

  // private final Sensor_Pixy m_pixy = new Sensor_Pixy();
  // AUTONOMOUS ROUTINES
  // A simple autonomous routine that shoots the loaded frisbees
  private final Command m_autoTest = new AutonomousTest(m_drivetrain, m_navX, m_limelight);
  private final Command m_taxiOnly = new Taxi(m_drivetrain);
  private final Command m_2Ball = new Taxi_2Ball(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker, m_limelight, m_hood, m_navX, m_common);
  private final Command m_3Ball = new Taxi_3Ball(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker, m_limelight, m_hood, m_navX, m_ODSHigh, m_common);
  private final Command m_5Ball = new Taxi_5Ball(m_drivetrain, m_intakeAcutate, m_intakeRoller, m_intakeCenterer, m_flywheel, m_kicker, m_limelight, m_hood, m_navX, m_ODSHigh, m_common);

  // autonomous chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  private XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  private XboxController m_gunnerController = new XboxController(OIConstants.kGunnerControllerPort);
  private JoystickAnalogButton m_driverTriggerR = new JoystickAnalogButton(m_driverController, 3);
  private JoystickAnalogButton m_driverTriggerL = new JoystickAnalogButton(m_driverController, 2);
  private JoystickAnalogButton m_gunnerTriggerR = new JoystickAnalogButton(m_gunnerController, 3);
  private JoystickAnalogButton m_gunnerTriggerL = new JoystickAnalogButton(m_gunnerController, 2);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureCompButtonBindings();

    // Configure default commands
    // Set the default drive command to tank drive
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain));
    m_intakeAcutate.setDefaultCommand(new IntakeRetract(m_intakeAcutate));
    // m_flywheel.setDefaultCommand(new Shoot(m_flywheel, m_limelight));

    // Configure autonomous options
    m_chooser.addOption("Auto Test", m_autoTest);
    m_chooser.addOption("Taxi", m_taxiOnly);
    m_chooser.addOption("2 Ball", m_2Ball);
    m_chooser.setDefaultOption("3 Ball", m_3Ball);
    // m_chooser.addOption("4 Ball", m_2Ball);
    m_chooser.addOption("5 Ball", m_5Ball);
    SmartDashboard.putData("Select Autonomous", m_chooser);

    // CameraServer.getInstance().startAutmomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureCompButtonBindings() {
    // RUNNER JOYSTICK
    // drivetrain is already handled above
    new JoystickButton(m_driverController, Button.kRightBumper.value).whenHeld(new IntakeRetract(m_intakeAcutate));
    new JoystickButton(m_driverController, Button.kLeftBumper.value).whenHeld(new IntakeDeploy(m_intakeAcutate));
    m_driverTriggerL.whenHeld(new ParallelCommandGroup(new Outtake(m_intakeRoller, m_intakeCenterer), 
                                                       new IntakeDeploy(m_intakeAcutate)));
    m_driverTriggerR.whenHeld(new ParallelCommandGroup(new IntakeODS(m_intakeRoller, m_intakeCenterer, m_kicker, m_ODSHigh), 
                                                       new IntakeDeploy(m_intakeAcutate)));
    new JoystickButton(m_driverController, Button.kB.value).whenHeld(new LimelightCenter(0.5, m_drivetrain, m_limelight, false));


    // GUNNER JOYSTICK
    new JoystickButton(m_gunnerController, Button.kY.value).whenHeld(new WinchOut(m_hangWinch));
    new JoystickButton(m_gunnerController, Button.kA.value).whenHeld(new WinchIn(m_hangWinch));
    new JoystickButton(m_gunnerController, Button.kX.value).whenHeld(new IntakeRetract(m_intakeAcutate));
    new JoystickButton(m_gunnerController, Button.kB.value).whenHeld(new IntakeDeploy(m_intakeAcutate));
    new POVButton(m_gunnerController, 0).whenHeld(new ActuateIn(m_hangActuate));
    new POVButton(m_gunnerController, 180).whenHeld(new ActuateOut(m_hangActuate));
    new JoystickButton(m_gunnerController, Button.kBack.value).whenHeld(new HoodUp(m_hood));
    new JoystickButton(m_gunnerController, Button.kStart.value).whenHeld(new HoodDown(m_hood));
    new POVButton(m_gunnerController, 90).whenHeld(new ShootReverse(m_kicker));
    new POVButton(m_gunnerController, 270).whenHeld(new HoodSetPosNew(m_hood, m_limelight));
    new JoystickButton(m_gunnerController, Button.kLeftStick.value).whenHeld(new ShootAssist(m_common, m_limelight, m_kicker, m_intakeCenterer, m_intakeRoller));
    new JoystickButton(m_gunnerController, Button.kRightBumper.value).whenHeld(new ShootSpinUp(m_common, m_flywheel, m_limelight, 2800, 2600));  //small/big
    m_gunnerTriggerR.whenHeld(new ParallelCommandGroup(new ShootSpinUp(m_common, m_flywheel, m_limelight), new HoodSetPosNew(m_hood, m_limelight)));
    
    new JoystickButton(m_gunnerController, Button.kLeftBumper.value).whenHeld(new ParallelCommandGroup(new IntakeODS(m_intakeRoller, m_intakeCenterer, m_kicker, m_ODSHigh), 
                                                       new IntakeDeploy(m_intakeAcutate)));
    // m_gunnerTriggerR.whenHeld(new ShootSpinUp(m_common, m_flywheel, m_limelight));
    // m_gunnerTriggerL.whenHeld(new ShootSpinUp(m_common, m_flywheel, m_limelight, 5500, 2500));  // 5500, 2500
  }

  public Drivetrain getRobotDrive() {
    return m_drivetrain;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}