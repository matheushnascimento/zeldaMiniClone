import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
  public static BufferedImage spritesheet, tileWall;
  public static BufferedImage[] player_front, enemy_front;

  public Spritesheet() {
    try {
      spritesheet = ImageIO.read(getClass().getResource("res/spritesheet.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }

    player_front = new BufferedImage[2];
    enemy_front = new BufferedImage[2];
    player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
    player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);

    enemy_front[0] = Spritesheet.getSprite(260, 251, 16, 16);
    enemy_front[1] = Spritesheet.getSprite(276, 251, 16, 16);

    tileWall = Spritesheet.getSprite(280, 221, 16, 16);
  }

  public static BufferedImage getSprite(int x, int y, int width, int height) {
    return spritesheet.getSubimage(x, y, width, height);
  }
}
// 160,258