#!/usr/bin/python

# We want to make the exsat amount from a number of $1 bills and $5 bills.
# Return True if it is possible to make the amount from the bills given and
# False if not.  Try to do it without using loops

def exact_change(ones, fives, amount):
    if amount % 5 > ones:
        return False
    elif amount - (fives * 5) > ones:
        return False
    else:
        return True
