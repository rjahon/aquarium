
public class Main {
    public static void main(String[] args) {
        Aquarium.TankInit();
        Fish[] fishes = new Fish[200]; // maximum tank capacity
        int num = 20;
        for (int i = 0; i < num; i++) {
            Fish fish = new Fish("fish" + i + 1);
            fishes[i] = fish;
            fishes[i].start();
        }
        displayTank(fishes, num);
    }

    static void displayTank(Fish[] fishes, int num) {
        boolean mating_status = false;
        int ms_cooldown = 0; // mating status cool down time
        for (int i = 0; i < 10000000; i++) { // aquarium display time
            int count = 0;

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\n\n\n\n\n\n");
            System.out.println(
                    "************************************************************************************************************************************************************************************************************************************************************");
            for (int j = 0; j < 50; j++) { // aquarium height
                System.out.print('*');

                for (int k = 0; k < 250; k++) { // aquarium width
                    if (Aquarium.grid[j][k].cellM
                            && Aquarium.grid[j][k].cellF
                            && !mating_status) { // if cell has two, gender different, fish they mate.
                        System.out.print('J'); // display 'JOIN' message: 'J'.
                        fishes[num] = new Fish("fish" + num + 1);
                        fishes[num].start(); // Create new fish.
                        num++; // Increment number of fish.
                        mating_status = true;
                        ms_cooldown = 2;
                    } else if (Aquarium.grid[j][k].cellM && !Aquarium.grid[j][k].cellF) {
                        System.out.print('M'); // if cell only has male fish, display 'M'.
                        count++;
                    } else if (!Aquarium.grid[j][k].cellM && Aquarium.grid[j][k].cellF) {// if cell only has
                        System.out.print('F'); // female fish, display 'F'.
                        count++;
                    } else {
                        System.out.print(' '); // if cell has no fish display ' '.
                    }
                }
                System.out.println("*");
            }
            System.out.println(
                    "************************************************************************************************************************************************************************************************************************************************************");
            System.out.println("Number of fish in the tank: " + count);
            System.out.printf("Did any fish mate in the past 3 seconds? " +
                    mating_status);

            if (mating_status) {
                if (ms_cooldown > 0) {
                    ms_cooldown--;
                } else {
                    mating_status = false;
                }
            }
            Aquarium.clear(); // clear the fish tank to avoid atomicity problems
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
