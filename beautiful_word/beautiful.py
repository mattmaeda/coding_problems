#!/bin/python3

# Return True if string begins and ends with the same character.

from re import compile
from re import match

def is_match(s):
    regularExpression = r'(?=.{2,})(.).*\1$|^(.)$'
    pattern = compile(regularExpression)

    if pattern.match(s):
        return True
    else:
        return False


if __name__ == "__main__":
    assert is_match("abcdsa") == True
    assert is_match("55") == True
    assert is_match("a") == True
    assert is_match("aba") == True
    assert is_match("5abwa5") == True
    assert is_match("abab") == False
    print("All tests passed!")
