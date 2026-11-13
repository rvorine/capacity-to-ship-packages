// greedy: load packages in order, start a new day when the next one won't fit
fun canShip(weights: IntArray, days: Int, capacity: Int): Boolean {
    var usedDays = 1
    var load = 0
    for (w in weights) {
        if (load + w > capacity) {
            usedDays++
            load = 0
        }
        load += w
    }
    return usedDays <= days
}

fun shipWithinDays(weights: IntArray, days: Int): Int {
    var lo = weights.max() // must carry the heaviest package
    var hi = weights.sum() // everything in one day

    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        if (canShip(weights, days, mid)) {
            hi = mid       // works, try a smaller capacity
        } else {
            lo = mid + 1   // too small
        }
    }
    return lo
}

fun main() {
    println(shipWithinDays(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5)) // 15
    println(shipWithinDays(intArrayOf(3, 2, 2, 4, 1, 4), 3))              // 6
    println(shipWithinDays(intArrayOf(1, 2, 3, 1, 1), 4))                 // 3
}
