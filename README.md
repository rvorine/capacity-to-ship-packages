[![Instagram](https://img.shields.io/badge/Instagram-lacopydepastel-E4405F?style=for-the-badge&logo=instagram&logoColor=white)](https://instagram.com/lacopydepastel)
[![YouTube](https://img.shields.io/badge/YouTube-%40rvorine-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://youtube.com/@rvorine)

# Capacity To Ship Packages Within D Days (LeetCode 1011)

## Problem

Packages with the given `weights` must be shipped **in order** from one port to another within `days` days. Each day the ship is loaded in order, never exceeding its weight **capacity**. Return the **least capacity** that ships everything within `days` days.

Example:

```
Input:  weights = [1,2,3,4,5,6,7,8,9,10], days = 5     Output: 15
Input:  weights = [3,2,2,4,1,4],          days = 3     Output: 6
Input:  weights = [1,2,3,1,1],            days = 4     Output: 3
```

## Solution

**Binary search on the answer**, with a greedy check.

The capacity has clear bounds:
- at least `max(weights)` — the ship must carry the heaviest package,
- at most `sum(weights)` — everything goes in one day.

Bigger capacity never needs more days, so "can ship within `days`?" flips from false to true exactly once. Binary search for that point:

- `canShip(capacity)`: walk the packages in order, adding to today's load. When the next package would exceed `capacity`, start a new day. Return `usedDays <= days`.
- If `canShip(mid)` is true, `mid` works — look lower (`hi = mid`).
- Otherwise look higher (`lo = mid + 1`).

When `lo == hi`, that is the smallest capacity that works.

**Time:** O(n · log(sum − max)) — each of the log steps does one O(n) check.
**Space:** O(1).

## Implementations

| Language | File |
|---|---|
| Python | [`python/ship_within_days.py`](python/ship_within_days.py) |
| Java | [`java/ShipWithinDays.java`](java/ShipWithinDays.java) |
| Kotlin | [`kotlin/ShipWithinDays.kt`](kotlin/ShipWithinDays.kt) |
| JavaScript | [`javascript/shipWithinDays.js`](javascript/shipWithinDays.js) |
| C# | [`csharp/ShipWithinDays.cs`](csharp/ShipWithinDays.cs) |
