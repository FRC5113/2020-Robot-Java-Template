
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import static frc.robot.Constants.ArmConstants.*;

public class ArmFalcon {

   private WPI_TalonFX armMotor;

   public ArmFalcon() {

      armMotor = new WPI_TalonFX(ARM_ID);

   }

   private void configureArm() {
   }

}