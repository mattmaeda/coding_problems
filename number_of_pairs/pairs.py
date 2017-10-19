#!/usr/bin/env python3

# Return the number of unique pairs from a list that sum up to a given integer

def numberOfPairs(a, k):
    uniques = set()

    for i,x in enumerate(a):
        idx = 0
        for y in a[i:]:
            if x == y and idx == 0:
                idx += 1
            elif x + y == k
                uniques.add("".join(str(n) for n in sorted([x,y])))

    return len(uniques)


if __name__ == "__main__":

    assert numberOfPairs([1,3,46,1,3,9], 47) == 1
    assert numberOfPairs([6,6,3,9,3,5,1], 12) == 2
    assert numberOfPairs([1,1,1,1], 2) == 1
    print("All tests passed!")
