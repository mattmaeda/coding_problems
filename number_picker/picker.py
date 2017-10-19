#!/usr/bin/env python3
#
# From hackerrank problem
# https://www.hackerrank.com/challenges/picking-numbers
#
# Given an array of integers, find and print the maximum number of integers you
# can select from the array such that the absolute difference between any two of
# the chosen integers is <= 1.
#
# Example:
#
# Given a list [4,5,6,3,3,1], the output should be 3 because we choose the
# following multiset of integers from the array: {4,3,3}. Each pair in the
# multiset has an absolute difference <= 1 (i.e., |4-3| = 1  and |3-3| = 0),
# so we print the number of chosen integers, , as our answer.

def proximity_searcher(num_list):
    max_count = 0
    uniques = set(num_list)
    for i in uniques:
        if num_list.count(i) > max_count:
            max_count = num_list.count(i)
        for x in uniques:
            if x != i and abs(i - x) <= 1:
                if (num_list.count(i) + num_list.count(x)) > max_count:
                    max_count = num_list.count(i) + num_list.count(x)



    return max_count


if __name__ == "__main__":
    #assert proximity_searcher([4,6,5,3,3,1]) == 3
    #assert proximity_searcher([1,2,2,3,1,2]) == 5
    assert proximity_searcher([84,43,11,41,2,99,31,32,56,53,
                               42,14,98,27,64,57,16,33,48,21,
                               46,61,87,90,28,12,62,49,29,77,
                               82,70,80,89,95,52,13,19,9,79,
                               35,67,51,39,7,1,66,8,17,85,71,
                               97,34,73,75,6,91,40,96,65,37,74,
                               20,68,23,47,76,55,24,3,30,22,55,
                               5,69,86,54,50,10,59,15,4,36,38,
                               83,60,72,63,78,58,88,93,45,18,
                               94,44,92,81,25,26]) == 3
    assert proximity_searcher([14,18,17,10,9,20,4,13,19,19,8,
                               15,15,17,6,5,15,12,18,2,18,7,20,
                               8,2,8,11,2,16,2,12,9,3,6,9,9,13,
                               7,4,6,19,7,2,4,3,4,14,3,4,9,17,9,
                               4,20,10,16,12,1,16,4,15,15,9,13,6,
                               3,8,4,7,14,16,18,20,11,20,14,20,12,
                               15,4,5,10,10,20,11,18,5,20,13,4,18,
                               1,14,3,20,19,14,2,5,13]) == 15
    assert proximity_searcher([14,18,17,10,9,20,4,13,19,19,8,15,
                               15,17,6,5,15,12,18,2,18,7,20,8,2,8,
                               11,2,16,2,12,9,3,6,9,9,13,7,4,6,19,
                               7,2,4,3,4,14,3,4,9,17,9,4,20,10,16,
                               12,1,16,4,15,15,9,13,6,3,8,4,7,14,
                               16,18,20,11,20,14,20,12,15,4,5,10,
                               10,20,11,18,5,20,13,4,18,1,14,3,20,
                               19,14,2,5,13]) == 15
    assert proximity_searcher([4,97,5,97,97,4,97,4,97,97,97,97,4,
                               4,5,5,97,5,97,99,4,97,5,97,97,97,5,
                               5,97,4,5,97,97,5,97,4,97,5,4,4,97,5,
                               5,5,4,97,97,4,97,5,4,4,97,97,97,5,5,
                               97,4,97,97,5,4,97,97,4,97,97,97,5,4,
                               4,97,4,4,97,5,97,97,97,97,4,97,5,97,
                               5,4,97,4,5,97,97,5,97,5,97,5,97,97,97]) == 50
    print("All tests passed!")
