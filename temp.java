import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class temp{
    private static final int NUM_SENSORS = 8;
    private static final int NUM_READINGS_PER_HOUR = 60;

    private static final int NUM_MINUTES_PER_READING = 1;
    private static final int NUM_READINGS_PER_REPORT = NUM_READINGS_PER_HOUR / 2;

    private static final int MAX_TEMP = 70;
    private static final int MIN_TEMP = -100;

    private static final Random random = new Random();

    private static volatile List<Integer>[] temperatureReadings = new List[NUM_SENSORS];

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < NUM_SENSORS; i++) {
            temperatureReadings[i] = Collections.synchronizedList(new ArrayList<>());
        }

        for (int i = 0; i < NUM_READINGS_PER_HOUR; i++) {
            for (int j = 0; j < NUM_SENSORS; j++) {
                int temperature = random.nextInt(MAX_TEMP - MIN_TEMP + 1) + MIN_TEMP;
                temperatureReadings[j].add(temperature);
            }

            if ((i + 1) % NUM_READINGS_PER_REPORT == 0) {
                Thread reportThread = new Thread(new TemperatureReportTask());
                reportThread.start();
                reportThread.join();
            }

            Thread.sleep(NUM_MINUTES_PER_READING * 1000);
        }
    }

    private static class TemperatureReportTask implements Runnable {

        @Override
        public void run() {
            List<Integer> allTemperatures = new ArrayList<>();

            for (int i = 0; i < NUM_SENSORS; i++) {
                allTemperatures.addAll(
                        temperatureReadings[i].subList(
                                temperatureReadings[i].size() - NUM_READINGS_PER_REPORT,
                                temperatureReadings[i].size()
                        )
                );
            }

            Collections.sort(allTemperatures);
            List<Integer> lowestTemperatures = allTemperatures.subList(0, 5);
            List<Integer> highestTemperatures = allTemperatures.subList(
                    allTemperatures.size() - 5,
                    allTemperatures.size()
            );

            int maxDiff = Integer.MIN_VALUE;
            int maxDiffStartTime = 0;

            for (int i = 0; i < NUM_READINGS_PER_REPORT - 1; i++) {
                int diff = Math.abs(
                        allTemperatures.get(i) - allTemperatures.get(i + 1)
                );
                if (diff > maxDiff) {
                    maxDiff = diff;
                    maxDiffStartTime = i;
                }
            }

            System.out.println("Lowest temperatures: " + lowestTemperatures);
            System.out.println("Highest temperatures: " + highestTemperatures);
            System.out.println(
                    "Max temperature difference: " +
                            maxDiff +
                            " starting at " +
                            (maxDiffStartTime * NUM_MINUTES_PER_READING) +
                            " minutes past the hour."
            );
        }
    }
}
