
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainFalcon;

public class DriveCommand extends CommandBase {

   private DriveTrainFalcon driveTrain;

   private DoubleSupplier leftHandler;
   private DoubleSupplier rightHandler;
   private double leftValue;
   private double rightValue;

   public DriveCommand(DoubleSupplier leftVal, DoubleSupplier rightVal, DriveTrainFalcon driveTrain) {
      this.driveTrain = driveTrain;
      addRequirements(driveTrain);
      this.leftHandler = leftVal;
      this.rightHandler = rightVal;
   }

   @Override
   public void initialize() {
      this.leftValue = 0.5 * Math.pow(leftHandler.getAsDouble(), 3) + 0.5 * (leftHandler.getAsDouble());
      this.rightValue = 0.5 * Math.pow(rightHandler.getAsDouble(), 3) + 0.5 * (leftHandler.getAsDouble());
   }

   @Override
   public void execute() {
      driveTrain.tankDrive(leftValue, rightValue);
   }

}