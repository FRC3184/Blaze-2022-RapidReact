/* Functions for the drivetrain subsystem */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.XboxController;

public class Controllers extends SubsystemBase {

  // Runner Controller
  private XboxController runController = new XboxController(0);
  // Gunner Controller
  private XboxController gunController = new XboxController(1);

  /** Creates a new Drivetrain. */
  public Controllers() {
    dashboardOut();
  }

  @Override
  public void periodic() {
    dashboardOut();

  }

  @Override
  public void simulationPeriodic() {

  }

  public void dashboardOut() {
    SmartDashboard.putNumber("Run LJoy Y", runController.getLeftY());
    SmartDashboard.putNumber("Run LJoy X", runController.getLeftX());
    SmartDashboard.putNumber("Run RJoy Y", runController.getRightY());
    SmartDashboard.putNumber("Run RJoy X", runController.getRightX());
    SmartDashboard.putBoolean("Run Joy A", runController.getAButton());
    SmartDashboard.putBoolean("Run Joy B", runController.getBButton());
    SmartDashboard.putBoolean("Run Joy X", runController.getXButton());
    SmartDashboard.putBoolean("Run Joy Y", runController.getYButton());

    SmartDashboard.putNumber("Run LJoy Y", gunController.getLeftY());
    
  }
}