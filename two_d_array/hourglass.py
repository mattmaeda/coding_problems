#!/usr/bin/env python3

#
# https://www.hackerrank.com/challenges/2d-array
#
# Given a 6 x 6 int array find the max sum for an hourglass

def max_hourglass(arr):
    # Programmatically build the hourglasses
    hourglasses = []

    for i in range(4):
        for x in range(4):
            hourglasses.append({
                i: [x, x+1, x+2],
                (i+1): [x+1],
                (i+2): [x, x+1, x+2]
            })

    max_sum = None
    for x in hourglasses:
        temp_arr = []
        for k in x.items():
            for i in k[1]:
                temp_arr.append(arr[k[0]][i])
        if max_sum is None:
            max_sum = sum(temp_arr)
        elif sum(temp_arr) > max_sum:
            max_sum = sum(temp_arr)

    print(max_sum)
    return max_sum




if __name__ == "__main__":

    arr = [[1,1,1,0,0,0], [0,1,0,0,0,0], [1,1,1,0,0,0],
           [0,0,2,4,4,0], [0,0,0,2,0,0], [0,0,1,2,4,0]]

    assert max_hourglass(arr) == 19

    arr = [[-1,-1,0,-9,-2,-2], [-2,-1,-6,-8,-2,-5],
           [-1,-1,-1,-2,-3,-4], [-1,-9,-2,-4,-4,-5],
           [-7,-3,-3,-2,-9,-9], [-1,-3,-1,-2,-4,-5]]

    assert max_hourglass(arr) == -6

    print("All tests passed!")
