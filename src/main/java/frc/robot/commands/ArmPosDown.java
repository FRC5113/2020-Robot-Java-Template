
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmFalcon;

public class ArmPosDown extends CommandBase {

   private ArmFalcon arm;

   public ArmPosDown(ArmFalcon arm) {
      this.arm = arm;
      addRequirements(arm);
   }

   @Override
   public void initialize() {
      if ((arm.getState() == 2) || (arm.getState() == 1)) {
         arm.setState(arm.getState() - 1);
      }
   }

   @Override
   public void execute() {
      switch(arm.getState()) {
         case 0:
            arm.setPosition(0);
            break;
         case 1:
            arm.setPosition(90);
            break;
      }
   }

   @Override
   public void end(boolean interrupted) {
      arm.stopMoving();
   }

   @Override
   public boolean isFinished() {
      switch(arm.getState()) {
         case 1:
            if (Math.abs(arm.getPosition() - 2*arm.getState()) < 5) {
               return true;
            }
         case 0:
            if (Math.abs(arm.getPosition() - 2*arm.getState()) < 5) {
               return true;
            }
         default:
            return false;
      } 
   }

}