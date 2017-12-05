import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int nShipping = in.nextInt();
        final int maxWeight = in.nextInt();

        final int[] shippings = new int[nShipping];
        for (int i = 0; i < nShipping; i++) {
            shippings[i] = in.nextInt();
        }
        System.out.println(minimalShippingsRequired(shippings, maxWeight));
    }

    private static int minimalShippingsRequired(int[] shippings, int maxWeight) {
        final List<Integer> filteredShippins = Arrays.stream(shippings).boxed()
                .filter(s -> s <= Integer.valueOf(maxWeight)).sorted((s1, s2) -> s2.compareTo(s2))
                .collect(Collectors.toList());
        int numShipping = 0;
        do {
            final List<Integer> removed = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < filteredShippins.size() && removed.size() < 2; i++) {
                if (sum + filteredShippins.get(i) <= maxWeight) {
                    removed.add(i);
                }
            }
            removed.parallelStream().forEach(r -> filteredShippins.remove(r.intValue()));
            numShipping++;
        } while (filteredShippins.size() > 0);
        return numShipping;
    }

}