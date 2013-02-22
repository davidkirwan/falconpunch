/*
Author: David Benson
Twitter: https://twitter.com/davidbensonIT
Github: https://github.com/D-Benson

Author: Dylan Conway
Twitter: https://twitter.com/Funi1234
Github: https://github.com/Funi1234

Author: David Kirwan
Twitter: https://twitter.com/kirwan_david
Github: https://github.com/davidkirwan

FalconPunch won 3rd place at the 2009 Irish National Robocode Competition

Released under the Attribution-NonCommercial-ShareAlike 3.0 Unported
http://creativecommons.org/licenses/by-nc-sa/3.0/
*/

package FalconPunch;
import robocode.*;


public class FalconPunch extends Robot
{
	private double ourEnergy = 0.0;
	private int boredYet = 0;
	private boolean huntingMode = false;
	/**
	 * run: FalconPunch's default behavior
	 */
	public void run() 
	{
		boredYet = 0;
		while(true) 
		{
			ourEnergy = getEnergy();
			boredYet++;
			ahead(500);
			
			if(boredYet > 8)
			{
				huntingMode = true;
				turnRadarRight(360);
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) 
	{
		if(!huntingMode)
		{
			// normal mode
			if(e.getDistance() < 250)
			{
				if(getEnergy() < 20)
				{
					fire(1.0);
				}
				else
				{
					fire(2.0);
				}
			}
			else
			{
				if(getEnergy() < 20)
				{
					fire(0.5);
				}
				else
				{
					fire(1.0);
				}
			}
		}
		else
		{
			// huntingmode.
			turnRight(e.getBearing());
			if(e.getDistance() < 300)
			{
				if(getEnergy() < 20)
				{
					fire(1.5);
				}
				else
				{
					fire(3.0);
				}
			}
			else
			{
				if(getEnergy() < 20)
				{
					fire(0.75);
				}
				else
				{
					fire(1.5);
				}
			}
		}
	}

	public void onHitRobot(HitRobotEvent e)
	{
		turnRight(e.getBearing()); //LEEERRROOOYYYY
		fire(3.0);
	}
	
	public void bulletHitEvent(BulletHitEvent e)
	{
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) 
	{
		double enemyHeading = e.getBearing();
		if(huntingMode)
		{
			turnRight(e.getBearing());
		}
		else
		{
			if(enemyHeading < 0)
			{
				turnRight(90 + enemyHeading);
			}
			else
			{
				turnRight(90 - e.getBearing()); // not sure if this is right.. but want to go 
											//90 degrees to the guy shooting us
			}
			if(e.getBearing() > 150 || e.getBearing() < -150)
			{
				ahead(500);
			}
			else
			{
				back(500);
			}
		}
		
	}

	/**
	 * onHitWall: What to do when you hit the wall
	 */
	public void onHitWall(HitWallEvent e)
	{
		double loc;
		loc = e.getBearing();
		if(loc > 0)
		{
			turnLeft(90);
		}
		else
		{
			turnRight(90);
		}
		
	}
	
}
