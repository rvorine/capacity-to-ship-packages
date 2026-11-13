def can_ship(weights, days, capacity):
    """Greedy: load packages in order, start a new day when the next one won't fit."""
    used_days, load = 1, 0
    for w in weights:
        if load + w > capacity:
            used_days += 1
            load = 0
        load += w
    return used_days <= days


def ship_within_days(weights, days):
    lo = max(weights)  # must carry the heaviest package
    hi = sum(weights)  # everything in one day

    while lo < hi:
        mid = lo + (hi - lo) // 2
        if can_ship(weights, days, mid):
            hi = mid        # works, try a smaller capacity
        else:
            lo = mid + 1    # too small

    return lo


if __name__ == "__main__":
    print(ship_within_days([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 5))  # 15
    print(ship_within_days([3, 2, 2, 4, 1, 4], 3))               # 6
    print(ship_within_days([1, 2, 3, 1, 1], 4))                  # 3
