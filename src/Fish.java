import java.util.Random;

public class Fish extends Thread {
    int TANK_HEIGHT = 50;
    int TANK_WIDTH = 250;
    int FISH_STEPS = 7; // fish movement duration if not interrupted
    int lifetime; // how much fish lives
    boolean gender; // male=true, female=false
    int x, y; // position
    String name;
    Random random = new Random();

    Fish(String name) {
        lifetime = random.nextInt(300) + 100;
        gender = random.nextBoolean(); // picks random gender
        x = random.nextInt(TANK_HEIGHT - 1) + 1; // and position 1-9
        y = random.nextInt(TANK_WIDTH - 1) + 1;
        this.name = name; // assigns name
    }

    @Override
    public void run() {
        while (lifetime > 0) {
            int i = random.nextInt(3) - 1; // picks randomly between -1, 0, and 1
            int j = random.nextInt(3) - 1; // to increment the position
            int k = random.nextInt(FISH_STEPS) + 3; // randomizes duration of movement
            while (k > 0) {
                x += i;
                y += j;
                // if fish reaches the border decrement the position and break out of the loop
                if ((x >= TANK_HEIGHT || x <= 0)
                        || (y >= TANK_WIDTH || y <= 0)) {
                    x -= i;
                    y -= j;
                    break;
                }
                Aquarium.updatePosition(x, y, gender); // update the position of the fish in the tank

                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // fish velocity
                k--;
            }
            // try {Thread.sleep(200);}
            // catch (InterruptedException e) {e.printStackTrace();}
            --lifetime;
        }
    }
}
