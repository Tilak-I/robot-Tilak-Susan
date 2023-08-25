// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
  private final WPI_TalonSRX backLeft;
  private final WPI_TalonSRX backRight;
  private final WPI_TalonSRX frontLeft; 
  private final WPI_TalonSRX frontRight;
  private final DifferentialDrive mRobotDrive;
  /** Creates a new Drivebase. */
  public Drivebase() {
    backLeft = new WPI_TalonSRX(0);
    backRight = new WPI_TalonSRX(0);
    frontLeft = new WPI_TalonSRX(0);
    frontRight = new WPI_TalonSRX(0);
    

    MotorControllerGroup LeftControlGroup = new MotorControllerGroup(frontLeft, backLeft);
    MotorControllerGroup RightControlGroup = new MotorControllerGroup(frontRight, backRight);
    
    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    mRobotDrive = new DifferentialDrive(LeftControlGroup, RightControlGroup);


  }

  public void defaultDrive(DoubleSupplier speed, DoubleSupplier rotation)
  {
    mRobotDrive.arcadeDrive(-speed.getAsDouble(), -rotation.getAsDouble());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  } 
}
