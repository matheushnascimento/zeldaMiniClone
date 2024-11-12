import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
  public static BufferedImage spritesheet, player_front, tileWall;

  public Spritesheet() {
    try {
      spritesheet = ImageIO.read(getClass().getResource("res/spritesheet.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }

    player_front = Spritesheet.getSprite(0, 11, 16, 16);
    tileWall = Spritesheet.getSprite(280, 221, 16, 16);
  }

  public static BufferedImage getSprite(int x, int y, int width, int height) {
    return spritesheet.getSubimage(x, y, width, height);
  }
}
