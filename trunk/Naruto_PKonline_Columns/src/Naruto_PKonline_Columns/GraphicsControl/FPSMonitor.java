//对于帧的处理
package Naruto_PKonline_Columns.GraphicsControl;

import java.awt.Color;
import java.awt.Graphics;

public class FPSMonitor
{
	private long lastTime;
	private long timeElapse;
	private int	FPS;
	public void Reset()
	{
		lastTime = System.nanoTime();
		FPS = 0;
		timeElapse = 0;
	}
	public void Update()
	{
		timeElapse = (System.nanoTime() - lastTime)/1000000;
		if(0 == timeElapse)
			return;
		lastTime = System.nanoTime();
		int _fps = (int) (1000 / timeElapse);
		if(0 == FPS)
			FPS = _fps;
		else
			FPS = (FPS + _fps)/2;
	}
	public long GetTimeElapse()
	{
		return timeElapse;
	}
	public int GetFPS()
	{
		return FPS;
	}
	public void DrawFPS(Graphics g)
	{
		Color colorOld = g.getColor();
		g.setColor(new Color(255,255,255));
		g.drawString(String.format("FPS: %d", FPS), 10, 20);
		g.setColor(colorOld);
	}
}
