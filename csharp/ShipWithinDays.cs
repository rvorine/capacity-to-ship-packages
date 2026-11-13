using System;
using System.Linq;

class ShipWithinDays
{
    // greedy: load packages in order, start a new day when the next one won't fit
    static bool CanShip(int[] weights, int days, int capacity)
    {
        int usedDays = 1, load = 0;
        foreach (int w in weights)
        {
            if (load + w > capacity)
            {
                usedDays++;
                load = 0;
            }
            load += w;
        }
        return usedDays <= days;
    }

    static int Solve(int[] weights, int days)
    {
        int lo = weights.Max(); // must carry the heaviest package
        int hi = weights.Sum(); // everything in one day

        while (lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (CanShip(weights, days, mid)) hi = mid; // works, try a smaller capacity
            else lo = mid + 1;                         // too small
        }
        return lo;
    }

    static void Main()
    {
        Console.WriteLine(Solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5)); // 15
        Console.WriteLine(Solve(new int[] { 3, 2, 2, 4, 1, 4 }, 3));              // 6
        Console.WriteLine(Solve(new int[] { 1, 2, 3, 1, 1 }, 4));                 // 3
    }
}
