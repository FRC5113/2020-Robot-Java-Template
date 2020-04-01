package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.XboxHandler.EnumButtons;

public class XboxButton extends JoystickButton {

	public XboxButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
	}

	public XboxButton(XboxController joystick, EnumButtons button) {
		super(joystick, button.value);
	}

	public XboxButton(XboxHandler joystick, EnumButtons button) {
		super(joystick, button.value);
	}

}