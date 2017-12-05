import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class DroneVinicius {
	public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int nDrones = in.nextInt();
        final int nMaintenance = in.nextInt();
        final int nRequired = in.nextInt();

        final List<Drone> drones = new ArrayList<>();
        final List<Integer> maintanancedIds = new ArrayList<>();

        for (int i = 0; i < nDrones; i++) {
            drones.add(new Drone(in.nextInt(), in.nextInt()));
        }
        for (int i = 0; i < nMaintenance; i++) {
            maintanancedIds.add(in.nextInt());
        }
        System.out.println(getDrones(drones, maintanancedIds, nRequired));
    }

    private static List<Integer> getDrones(List<Drone> drones, List<Integer> maintanancedIds, int nRequired) {
    	Comparator<Drone> comp = new Comparator<Drone>() {

			@Override
			public int compare(Drone o1, Drone o2) {
				if(o1.getDistance()>o2.getDistance()){
					return 1;
				}
				return 0;
			}
		};
		TreeSet<Drone> available = new TreeSet<>(comp);
    	for (Drone drone : drones) {
			if(maintanancedIds.contains(drone.getId())){
				available.add(drone);
				if(available.size() == nRequired){
					break;
				}
			
			}
		}
		return null;
    }
    
    
}
