import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.util.StopWatch;

public class DroneSerra {
	public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int nDrones = 1000000;// in.nextInt(); 
        final int nMaintenance = 5000;// in.nextInt();
        final int nRequired = 1000;// in.nextInt();

        final List<Drone> drones = new ArrayList<>();
        final List<Integer> maintanancedIds = new ArrayList<>();

        final Random random = new Random();

        for (int i = 0; i < nDrones; i++) {
            // drones.add(new Drone(in.nextInt(), in.nextInt()));
            drones.add(new Drone(i, random.nextInt(nDrones)));
        }
        for (int i = 0; i < nMaintenance; i++) {
            // maintanancedIds.add(in.nextInt());
            maintanancedIds.add(random.nextInt(nDrones));
        }
//        StopWatch stopWatch1 = new StopWatch();
//        stopWatch1.start();
//        System.out.println(getDrones(drones, maintanancedIds, nRequired));
//        stopWatch1.stop();
//        System.out.println(stopWatch1.shortSummary());

//        StopWatch stopWatch2 = new StopWatch();
//        stopWatch2.start();
//        System.out.println(getDrones2(drones, maintanancedIds, nRequired));
//        stopWatch2.stop();
//        System.out.println(stopWatch2.shortSummary());
        
    }

    private static List<Integer> getDrones(List<Drone> drones, List<Integer> maintanancedIds, int nRequired) {
        return drones.parallelStream()
                .filter(d -> !maintanancedIds.contains(d.getId()))
                .sorted((d1, d2) -> Integer.compare(d2.getDistance(), d1.getDistance()))
                .limit(nRequired)
                .map(Drone::getId)
                .collect(Collectors.toList());
    }

    private static List<Integer> getDrones2(List<Drone> drones, List<Integer> maintanancedIds, int nRequired) {
        final List<Drone> sortedDrones = drones.parallelStream()
                .sorted((d1, d2) -> Integer.compare(d2.getDistance(), d1.getDistance()))
                .collect(Collectors.toList());
        final List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < sortedDrones.size() && selected.size() < nRequired; i++) {
            final int dId = sortedDrones.get(i).getId();
            if (!maintanancedIds.contains(dId)) {
                selected.add(dId);
            }
        }
        return selected;
    }
    
}
