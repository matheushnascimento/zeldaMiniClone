import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Rectangle {
  public boolean right = false, up = false, down = true, left = false, shoot = false;
  public int spd = 4, curAnimation = 0, curFrames = 0, targetFrames = 15, dir;
  public static List<Bullet> bullets = new ArrayList<Bullet>();

  public Enemy(int x, int y) {
    super(x, y, 32, 32);
  }

  public void tick() {
    boolean moved = false;
    if (right && World.isFree(x + spd, y)) {
      x++;
      moved = true;
      dir = 1;
    } else if (left && World.isFree(x - spd, y)) {
      x -= spd;
      moved = true;
      dir = -1;
    }

    if (up && World.isFree(x, y - spd)) {

      y -= spd;
      moved = true;
    } else if (down && World.isFree(x, y + spd)) {

      y += spd;
      moved = true;
    }
    if (moved) {
      curFrames++;
      if (curFrames == targetFrames) {
        curFrames = 0;
        curAnimation++;
        if (curAnimation == Spritesheet.player_front.length) {
          curAnimation = 0;
        }
      }
    }

    if (shoot) {
      shoot = false;
      bullets.add(new Bullet(x, y, dir));

    }
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).tick();
    }
  }

  public void render(Graphics g) {
    g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).render(g);
    }
  }
}
