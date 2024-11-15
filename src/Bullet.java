import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {
  public int dir = 1, speed = 8, frames = 0;

  public Bullet(int x, int y, int dir) {
    super(x, y, 10, 10);
    this.dir = dir;
  }

  public void tick() {
    x += speed * dir;
    frames++;
    if (frames == 60) {
      Player.bullets.remove(this);
      return;
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillOval(x + 16, y, width, height);

  }
}
