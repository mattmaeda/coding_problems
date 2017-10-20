#!/usr/bin/env python3

# Solution for https://www.hackerrank.com/challenges/array-left-rotation

def leftRotation(a, d):
    """ Left rotate list a d times"""
    x = len(a)
    if x > d:
        return a[d:] + a[:d]
    else:
        idx = x - (d % x)
        return a[idx:] + a[:idx]


if __name__ == "__main__":
    assert leftRotation([1,2,3,4,5], 4) == [5,1,2,3,4]
    print("All tests passed!")
