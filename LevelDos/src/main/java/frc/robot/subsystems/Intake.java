// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeMotor;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class Intake extends SubsystemBase {
  
  final DoubleSolenoid solenoid1;
  final DoubleSolenoid solenoid2;
  final WPI_TalonSRX intakeMotor;
  /** Creates a new Intake. */
  public Intake()
   {  
    solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0,1);
    solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2,3);
    intakeMotor = new WPI_TalonSRX(IntakeMotor.kIntakeMotorPort);
  

   }
   
   public void SolenoidExtend()
   {
    solenoid1.set(kForward);
    solenoid2.set(kForward);
   }

   public void SolenoidRetract()
   {
    solenoid1.set(kReverse);
    solenoid2.set(kReverse);
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
