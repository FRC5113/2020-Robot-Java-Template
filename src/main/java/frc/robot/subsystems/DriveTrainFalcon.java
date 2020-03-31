
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveTrainConstants.*;

/**
 * This is the subsytem for the drivetrain, assuming it's tank. Which it always
 * better be. 8-wheel, west-coast tank drive with 4 motors and a dropcenter
 */

public class DriveTrainFalcon extends SubsystemBase {

   /**
    * ALWAYS ALWAYS declare variables that are in the scope of one class (variables
    * that are only important to single class) as private. If you don't I'll haunt
    * you. Seriously. But this is where all of motors and stuff are going to be
    * defined, but not yet configured. Do that in the constructor
    */
   private final WPI_TalonFX leftMaster;
   private final WPI_TalonFX rightMaster;
   private final WPI_TalonFX leftSlave;
   private final WPI_TalonFX rightSlave;

   private final DifferentialDrive driveBase;

   public DriveTrainFalcon() {
      // Configuring the motors based on the device ID's set in Phoenix Tuner
      leftMaster = new WPI_TalonFX(LEFT_SLAVE_ID);
      rightMaster = new WPI_TalonFX(RIGHT_MASTER_ID);
      leftSlave = new WPI_TalonFX(LEFT_SLAVE_ID);
      rightSlave = new WPI_TalonFX(RIGHT_SLAVE_ID);

      // The whole configureMotor method comes from being lazy ...
      // Lesson #1: learn how to be lazy and innovative
      configureMotor(leftMaster, false);
      configureMotor(rightMaster, true);
      configureMotor(leftSlave, false);
      configureMotor(rightSlave, true);

      // Setting the slaves to follow the master
      rightSlave.set(ControlMode.Follower, rightMaster.getDeviceID());
      leftSlave.set(ControlMode.Follower, leftMaster.getDeviceID());

      driveBase = new DifferentialDrive(leftMaster, rightMaster);
      driveBase.setDeadband(DEADBAND);

   }

   private void configureMotor(WPI_TalonFX motor, boolean right) {
      motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there before configuring
      motor.configVoltageCompSaturation(VOLTAGE_COMPENSATION); // only use 12.3 volts regardless of battery voltage
      motor.enableVoltageCompensation(true); // enable ^
      motor.setNeutralMode(NeutralMode.Brake); // set it so that when the motor is getting no input, it stops
      motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
      motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
      motor.configOpenloopRamp(RAMP_RATE); // how long it takes to go from 0 to the set speed
      motor.setSensorPhase(right);
      // Make sure that both sides' encoders are getting positive values when going
      // forward
   }

   public void tankDrive(double leftSpeed, double rightSpeed) {
      // Controlling the left side and the right side seperately
      driveBase.tankDrive(leftSpeed, rightSpeed);
   }

   public void tankDriveVolts(double leftSpeed, double rightSpeed) {
      // Tank drive, but in the case we want to use volts, it's here
      leftMaster.setVoltage(12.3 * leftSpeed);
      rightMaster.setVoltage(12.3 * rightSpeed);
   }

   public void arcadeDrive(double speed, double rotation) {
      // How fast you want to go forward, and how much you want to turn
      driveBase.arcadeDrive(speed, rotation);
   }

   public void curvatureDrive(double speed, double rotation) {
      // Curvature drive is subset of arcade drive seems interesting ... I'll try
      // testing it
      driveBase.curvatureDrive(speed, rotation, true);

      /**
       * These should help to control curvature drive, but testing needs to be done
       * driveBase.setQuickStopAlpha(something);
       * driveBase.setQuickStopThreshold(something);
       */
   }

   public void triggerDrive(double forward, double reverse, double rotation) {
      // Basically how driving works in Forza, uses triggers
      driveBase.arcadeDrive(forward - reverse, rotation);
   }

   public double getLeftPos() {
      return leftMaster.getSelectedSensorPosition()*POSITION_CONVERSION_FALCON;
   }

   public double getRightPos() {
      return rightMaster.getSelectedSensorPosition()*POSITION_CONVERSION_FALCON;
   }

   public double getLeftVel() {
      return leftMaster.getSelectedSensorVelocity()*POSITION_CONVERSION_FALCON;
   }

   public double getRightVel() {
      return rightMaster.getSelectedSensorPosition()*POSITION_CONVERSION_FALCON;
   }

   public void setAllToCoast() {
      leftMaster.setNeutralMode(NeutralMode.Coast);
      rightMaster.setNeutralMode(NeutralMode.Coast);
      leftSlave.setNeutralMode(NeutralMode.Coast);
      rightSlave.setNeutralMode(NeutralMode.Coast);
   }

}