// greedy: load packages in order, start a new day when the next one won't fit
function canShip(weights, days, capacity) {
  let usedDays = 1, load = 0;
  for (const w of weights) {
    if (load + w > capacity) {
      usedDays++;
      load = 0;
    }
    load += w;
  }
  return usedDays <= days;
}

function shipWithinDays(weights, days) {
  let lo = Math.max(...weights);                    // must carry the heaviest package
  let hi = weights.reduce((sum, w) => sum + w, 0);  // everything in one day

  while (lo < hi) {
    const mid = lo + Math.floor((hi - lo) / 2);
    if (canShip(weights, days, mid)) hi = mid;      // works, try a smaller capacity
    else lo = mid + 1;                              // too small
  }
  return lo;
}

console.log(shipWithinDays([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 5)); // 15
console.log(shipWithinDays([3, 2, 2, 4, 1, 4], 3));              // 6
console.log(shipWithinDays([1, 2, 3, 1, 1], 4));                 // 3

module.exports = { shipWithinDays };
