public class Aquarium {
    static int TANK_HEIGHT = 50;
    static int TANK_WIDTH = 250;

    public static TankCells[][] grid = new TankCells[TANK_HEIGHT + 1][TANK_WIDTH + 1];

    public static void TankInit() {
        for (int i = 0; i <= TANK_HEIGHT; i++) {
            for (int j = 0; j <= TANK_WIDTH; j++) {
                grid[i][j] = new TankCells();
            }
        }
    }

    static void updatePosition(int x, int y, boolean gender) {
        if (gender) {
            grid[x][y].cellM = true; // if true fill the cell with male.
        } else {
            grid[x][y].cellF = true; // else - with female
        }
    }

    static void clear() {
        for (int i = 0; i <= TANK_HEIGHT; i++) {
            for (int j = 0; j <= TANK_WIDTH; j++) {
                grid[i][j].cellM = false;
                grid[i][j].cellF = false;
            }
        }
    }
}

class TankCells {
    boolean cellM = false;
    boolean cellF = false;
}