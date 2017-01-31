package org.usfirst.frc103.Swerve2017Test.subsystems;

import static org.usfirst.frc103.Swerve2017Test.RobotMap.driveLeftFront;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.driveLeftRear;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.driveRightFront;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.driveRightRear;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.steerLeftFront;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.steerLeftRear;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.steerRightFront;
import static org.usfirst.frc103.Swerve2017Test.RobotMap.steerRightRear;

import org.usfirst.frc103.Swerve2017Test.commands.SwerveDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

	public static final double ENCODER_COUNT_PER_ROTATION = 1024.0;
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new SwerveDrive());
    }
	
	public void swerveDrive(double vX, double vY, double omega) {
        double wL2 = omega * (28.0 / 2.0), wW2 = omega * (22.0 / 2.0);
        double A = vX - wL2, B = vX + wL2, C = vY - wW2, D = vY + wW2;
        double speedLF = speed(B, D), speedLR = speed(A, D), speedRF = speed(B, C), speedRR = speed(A, C);
    	double angleLF = angle(B, D), angleLR = angle(A, D), angleRF = angle(B, C), angleRR = angle(A, C);
    	double maxSpeed = Math.max(Math.max(Math.max(speedLF, speedLR), Math.max(speedRF, speedRR)), 1.0);

    	setSwerveModule(steerLeftFront, driveLeftFront, angleLF, speedLF / maxSpeed);
    	setSwerveModule(steerLeftRear, driveLeftRear, angleLR, speedLR / maxSpeed);
    	setSwerveModule(steerRightFront, driveRightFront, angleRF, speedRF / maxSpeed);
    	setSwerveModule(steerRightRear, driveRightRear, angleRR, speedRR / maxSpeed);
	}
	
	private double speed(double val1, double val2){
    	return Math.sqrt(Math.pow(val1, 2) + Math.pow(val2, 2));
    }
    
    private double angle(double val1, double val2){
    	return Math.atan2(val1, val2) * (180.0 / Math.PI);
    }
    
    private void setSwerveModule(CANTalon steer, CANTalon drive, double angle, double speed) {
    	double currentPosition = steer.getPosition();
    	double currentAngle = (currentPosition * 360.0 / ENCODER_COUNT_PER_ROTATION) % 360.0;
    	if (currentAngle > 180.0) currentAngle -= 360.0;
    	double targetAngle = -angle;
    	double deltaDegrees = targetAngle - currentAngle;
    	if (Math.abs(deltaDegrees) > 180.0) {
    		deltaDegrees -= 360.0 * Math.signum(deltaDegrees);
    	}
    	if (Math.abs(deltaDegrees) > 90.0) {
    		deltaDegrees -= 180.0 * Math.signum(deltaDegrees);
    		speed = -speed;
    	}
    	double targetPosition = currentPosition + (deltaDegrees * ENCODER_COUNT_PER_ROTATION / 360.0);
    	steer.setSetpoint(targetPosition);
    	drive.set(speed);
    }
}
