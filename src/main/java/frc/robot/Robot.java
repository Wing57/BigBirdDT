// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  // talons
  private final WPI_TalonFX leftMotor1 = new WPI_TalonFX(0);
  private final WPI_TalonFX leftMotor2 = new WPI_TalonFX(1);
  private final WPI_TalonFX leftMotor3 = new WPI_TalonFX(2);

  private final WPI_TalonFX rightMotor1 = new WPI_TalonFX(3);
  private final WPI_TalonFX rightMotor2 = new WPI_TalonFX(4);
  private final WPI_TalonFX rightMotor3 = new WPI_TalonFX(5);

  //Motor groups
  private final MotorControllerGroup leftMotorGroup = new MotorControllerGroup(leftMotor1, leftMotor2, leftMotor3);
  private final MotorControllerGroup rightMotorGroup = new MotorControllerGroup(rightMotor1, rightMotor2, rightMotor3);

  // Differentialdrive
  private final DifferentialDrive Drivetrain = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  //Joystick
  Joystick stick = new Joystick(0);

  //XboxController
  XboxController xbox = new XboxController(1);

  // Pnuematics
  private final Compressor comp = new Compressor(2, PneumaticsModuleType.REVPH);
  private final DoubleSolenoid solenoid = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 1, 0);

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    comp.disable();

  }

  @Override
  public void teleopPeriodic() {

    Drivetrain.arcadeDrive(stick.getY(), stick.getZ());

    if (xbox.getLeftBumper()) {
      
      solenoid.set(DoubleSolenoid.Value.kForward);
      
    } else if (xbox.getRightBumper()) {
      
      solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    if (xbox.getAButton()) {
      
      comp.enableDigital();
    
    } else if (xbox.getBButton()) {

      comp.disable();
    }
  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
