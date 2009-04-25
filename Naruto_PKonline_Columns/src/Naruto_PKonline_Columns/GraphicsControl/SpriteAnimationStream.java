//对于图像的流处理
package Naruto_PKonline_Columns.GraphicsControl;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteAnimationStream {

    public boolean IsDrawAgain = true;
    public boolean StopDraw = false;
    public int frameNumber;
    private int fps;
    public long timeElapse;
    public int currentFrame;
    private String fileName;
    public int startNumber;
    Image img = null;

    public void Create(String fileName, int frameNumber, int startNumber, int fps) {
        timeElapse = 0;
        this.fps = fps;
        this.frameNumber = frameNumber;
        currentFrame = 0;
        this.fileName = fileName;
        this.startNumber = startNumber;
    }

    public void Update(long timeElapse) {
        this.timeElapse += timeElapse;
        currentFrame = (int) ((this.timeElapse / (1000 / fps)) % frameNumber) + startNumber;
        try {
            img = ImageIO.read(new File(String.format(fileName, currentFrame)));
        } catch (Exception e) {
            System.out.println("SpriteAnimation Create Error: Image not Exist");
        }
    }

    public void Update_back(long timeElapse) {
        this.timeElapse += timeElapse;
        currentFrame = startNumber - (int) ((this.timeElapse / (1000 / fps)) % frameNumber);
        try {
            img = ImageIO.read(new File(String.format(fileName, currentFrame)));
        } catch (Exception e) {
            System.out.println("SpriteAnimation Create Error: Image not Exist");
        }
    }

    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

    public void One_Draw(Graphics g, int x, int y, int cx, int cy) {
        if (this.IsDrawAgain) {
            g.drawImage(img, x, y, x + cx, y + cy, 0, 0, img.getWidth(null), img.getHeight(null), null);
        } else {
            return;
        }
        if (currentFrame >= startNumber + frameNumber - 1) {
            this.IsDrawAgain = false;
        }
        if (currentFrame == startNumber + frameNumber - 2) {
            this.StopDraw = true;
        }

    }

    public void One_Draw_back(Graphics g, int x, int y, int cx, int cy) {
        if (this.IsDrawAgain) {
            g.drawImage(img, x, y, x + cx, y + cy, 0, 0, img.getWidth(null), img.getHeight(null), null);
        } else {
            return;
        }
        if (currentFrame <= startNumber - frameNumber + 1) {
            this.IsDrawAgain = false;
        }
        if (currentFrame == startNumber - frameNumber + 2) {
            this.StopDraw = true;
        }
    }

    public void Draw(Graphics g, int x, int y, int cx, int cy) {
        g.drawImage(img, x, y, x + cx, y + cy, 0, 0, img.getWidth(null), img.getHeight(null), null);
    }
}
