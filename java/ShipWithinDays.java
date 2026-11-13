public class ShipWithinDays {

    // greedy: load packages in order, start a new day when the next one won't fit
    static boolean canShip(int[] weights, int days, int capacity) {
        int usedDays = 1, load = 0;
        for (int w : weights) {
            if (load + w > capacity) {
                usedDays++;
                load = 0;
            }
            load += w;
        }
        return usedDays <= days;
    }

    static int shipWithinDays(int[] weights, int days) {
        int lo = 0, hi = 0;
        for (int w : weights) {
            lo = Math.max(lo, w); // must carry the heaviest package
            hi += w;              // everything in one day
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, days, mid)) {
                hi = mid;         // works, try a smaller capacity
            } else {
                lo = mid + 1;     // too small
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)); // 15
        System.out.println(shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));              // 6
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));                 // 3
    }
}
