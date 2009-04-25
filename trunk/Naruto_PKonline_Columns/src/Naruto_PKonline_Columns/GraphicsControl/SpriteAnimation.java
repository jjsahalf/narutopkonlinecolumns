//直接读入内存的处理
package Naruto_PKonline_Columns.GraphicsControl;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class SpriteAnimation {
        private int frameNumber;
	private int fps;
	private long timeElapse;
	private int currentFrame;
	Image[] img;
	public void Create(String fileName, int frameNumber, int startNumber, int fps)
	{
		timeElapse = 0;
		this.fps = fps;
		this.frameNumber = frameNumber;
		currentFrame = 0;
		img = new Image[frameNumber];
		for(int i = startNumber; i<frameNumber + startNumber; i++)
		{
			try
			{
				img[i-startNumber] = ImageIO.read(new File(String.format(fileName, i)));
			}
			catch(Exception e)
			{
				System.out.println("SpriteAnimation Create Error: Image not Exist");
			}
		}
	}
	public void Update(long timeElapse)
	{
		this.timeElapse += timeElapse;
		currentFrame = (int) ((this.timeElapse/(1000/fps))%frameNumber);
	}
	public void Draw(Graphics g, int x, int y)
	{
		g.drawImage(img[currentFrame], x, y, null);
	}
	public void Draw(Graphics g, int x, int y, int cx, int cy)
	{
		g.drawImage(img[currentFrame], x, y, x + cx, y + cy, 0, 0, img[currentFrame].getWidth(null), img[currentFrame].getHeight(null), null);
	}
}
