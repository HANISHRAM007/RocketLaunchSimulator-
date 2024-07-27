import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Rocket {

    private String stage;
   // private String second;
    private double fuel;
    private double altitude;
    private double speed;
    private boolean checksCompleted;

    public Rocket() {
        this.stage = "Pre-Launch";
        this.fuel = 100.0;
        this.altitude = 0.0;
        this.speed = 0.0;
        this.checksCompleted = false;
    }

    public void startChecks() {
        checksCompleted = true;
        System.out.println("All systems are 'READY' for launch.");
    }

    public void launch() throws InterruptedException {
        if (!checksCompleted) {
            System.out.println("Pre-launch checks not completed.");
            return;
        }

        stage = "Stage 1";
        System.out.println("Launch initiated.");
        sleep(1000);
        System.out.println("Launch successful");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateStatus(1);
                if (stage.equals("Orbit") || stage.equals("Mission Failed")) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
    public void fastForward(int se) {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Launch not initiated.");
            return;
        } else {
       fuel -=se;
       altitude += se;
       speed += se;
       printStatus();
                
        }
    
}

    private void updateStatus(int seconds) {
        for (int i = 0; i < seconds; i++) {
            if (stage.equals("Stage 1")) {
                fuel -= 1;
                altitude += 10;
                speed += 100;
                if (fuel <= 50) {
                    stage = "Stage 2";
                    System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
                    continue;
                }
            } else if (stage.equals("Stage 2")) {
                fuel -= 1;
                altitude += 20;
                speed += 200;
//                if(speed==)
                if (fuel <= 0) {
                    stage = "Mission Failed";
                    System.out.println("Mission Failed due to insufficient fuel.");
                    break;
                }
                if (altitude >= 200) {
                    stage = "Orbit";
                    System.out.println("Orbit achieved! Mission Successful.");
                    break;
                }
            }
        }
    }

    public void printStatus() {
        System.out.printf("Stage: %s, Fuel: %.2f%%, Altitude: %.2f km, Speed: %.2f km/h%n",
                stage, fuel, altitude, speed);
    }
}
public class RocketLaunchSimulator {
    

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hi Welcome to the Rocket Sumilator");
            System.out.println("This Rocket Launch Simulator simulates a rocket launch with real-time updates.\n" +
" * It includes pre-launch checks, launch initiation, and fast-forwarding capabilities.\n" +
" * The simulation provides status updates on the rocket's stage, fuel, altitude, and speed.");
        System.out.println("->Enter 'c'for the 'pre-launch checks'\n ->Enter 'l' for 'Launch initiation'\n ->Enter'f' for 'FastForward'\n ->Enter's' for'Checking the status'\n ->Enter'exit' for'Quit the sumilator'");
        
        Scanner scanner = new Scanner(System.in);
        Rocket rocket = new Rocket();
        String input;
        int seconds;

        do{
            
            System.out.print("Enter command: ");
            input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            
            switch (command) {
                case "c":
                    rocket.startChecks();
                    break;
                case "l":
                    rocket.launch();
                    break;
                case "f": {
                    System.out.println("Enter the seconds to fast forward: ");
                    int se=scanner.nextInt();
                   rocket.fastForward(se);
                    break;
                }

                case "s":
                    rocket.printStatus();
                    break;
                case "exit":
                System.out.println("Exiting simulator.");
                scanner.close();
                System.exit(0);

            }
        }while(input!="exit");

    
    }
}

