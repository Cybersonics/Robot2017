package org.usfirst.frc103.Swerve2017Test.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.climberLiftWinch;

import org.usfirst.frc103.Swerve2017Test.Robot;
import org.usfirst.frc103.Swerve2017Test.RobotMap;
import org.usfirst.frc103.Swerve2017Test.subsystems.Climber;

import static org.usfirst.frc103.Swerve2017Test.RobotMap.*;
import com.ctre.CANTalon;

public class ClimbSpin extends Command {
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}

	
	public ClimbSpin(){
		
		requires(Robot.climber);
		
	}
	
	
	protected void initialize(){
		
		
		
	}
	
	protected void execute(){
		
		
		
	}
	
	protected void end(){
		
		
		
	}
	
	
	
	
}
