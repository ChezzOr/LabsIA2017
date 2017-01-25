import lejos.nxt.*;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class movement {
	public static void main (String args[]){
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);
		int actual = 0;
		int limit = 20;
		for(int x=0;x<4;x++){
			Motor.A.forward();
			Motor.C.forward();
			actual = sonic.getDistance();
			LCD.clear();
			LCD.drawString(sonic.getVendorID(), 0, 0);
		    LCD.drawString(sonic.getProductID(), 0, 1);
		    LCD.drawString(sonic.getVersion(), 0, 2);
		    LCD.drawInt(sonic.getDistance(), 0, 3);
			for(int i=0; i<50;i++){
				LCD.clear();
			    LCD.drawString(sonic.getVendorID(), 0, 0);
			    LCD.drawString(sonic.getProductID(), 0, 1);
			    LCD.drawString(sonic.getVersion(), 0, 2);
			    LCD.drawInt(sonic.getDistance(), 0, 3);
				try {
					actual = sonic.getDistance();
					if(actual < limit){
						Motor.A.backward();
						Motor.C.backward();
						while(actual < limit){
							actual = sonic.getDistance();
						}
						Motor.A.stop();
						Motor.C.stop();
						break;
					}
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Motor.C.stop();
			Sound.beep();
			Motor.A.forward();
			Motor.C.backward();
			for(int i=0; i<7; i+=1){
				LCD.clear();
			    LCD.drawString(sonic.getVendorID(), 0, 0);
			    LCD.drawString(sonic.getProductID(), 0, 1);
			    LCD.drawString(sonic.getVersion(), 0, 2);
			    LCD.drawInt(sonic.getDistance(), 0, 3);
				try {
					actual = sonic.getDistance();
					if(actual < limit){
						Motor.A.backward();
						Motor.C.forward();
						while(actual < 20){
							actual = sonic.getDistance();
						}
						Motor.A.stop();
						Motor.C.stop();
						break;
					}
					Thread.sleep((long) .25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Motor.A.stop();
		Motor.C.stop();
		Sound.beep();
		Sound.beep();
	}
}
