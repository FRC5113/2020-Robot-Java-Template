
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ArmConstants.*;
import static frc.robot.Constants.GeneralConstants.*;

public class ArmFalcon extends SubsystemBase {

   private WPI_TalonFX armMotor;
   private int state;

   public ArmFalcon() {
      armMotor = new WPI_TalonFX(ARM_ID);
      configureArm();
      state = 0;
   }

   private void configureArm() {
      armMotor.configFactoryDefault();
      armMotor.configVoltageCompSaturation(VOLTAGE_COMPENSATION);
      armMotor.enableVoltageCompensation(true); // enable ^
      armMotor.setNeutralMode(NeutralMode.Brake); // set it so that when the motor is getting no input, it stops
      armMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
      armMotor.setSelectedSensorPosition(0);
      armMotor.configForwardSoftLimitThreshold(((int) (0 / POSITION_TO_DEGREE_FALCON)));
      armMotor.configForwardSoftLimitEnable(true);
      armMotor.configReverseSoftLimitThreshold((int) (180 / POSITION_TO_DEGREE_FALCON));
      armMotor.configReverseSoftLimitEnable(true);
   }

   public void setPosition(double degrees) {
      armMotor.set(ControlMode.Position, degrees / POSITION_TO_DEGREE_FALCON);
      if (degrees == 90)
         setState(1);
      else if (degrees == 180)
         setState(2);
      else
         setState(0);
   }

   public double getPosition() {
      return armMotor.getSelectedSensorPosition() * POSITION_TO_DEGREE_FALCON;
   }

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public void stopMoving() {
      armMotor.set(0);
   }

}