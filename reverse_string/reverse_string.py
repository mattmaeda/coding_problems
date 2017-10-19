#!/usr/bin/python

def reverse_string(original_string):
    reversed_string = ""
    str_length = len(original_string)

    for i in range(str_length):
        idx = (str_length - 1) - i
        reversed_string += original_string[idx]

    return reversed_string


def reverse_string2(original_string):
    reversed_string = ""

    for i in range(len(original_string)):
        reversed_string = "{}{}".format(original_string[i], reversed_string)

    return reversed_string


def reverse_string3(original_string):
    reversed_string = ""

    for i in range((len(original_string)-1), -1, -1):
        reversed_string += original_string[i]

    return reversed_string


def reverse_string4(original_string):
    return original_string[::-1]


if __name__ == "__main__":
    assert reverse_string("matt maeda") == "adeam ttam"
    assert reverse_string("123456789") == "987654321"
    assert reverse_string(" abc ") == " cba "

    assert reverse_string2("matt maeda") == "adeam ttam"
    assert reverse_string2("123456789") == "987654321"
    assert reverse_string2(" abc ") == " cba "

    assert reverse_string3("matt maeda") == "adeam ttam"
    assert reverse_string3("123456789") == "987654321"
    assert reverse_string3(" abc ") == " cba "

    assert reverse_string4("matt maeda") == "adeam ttam"
    assert reverse_string4("123456789") == "987654321"
    assert reverse_string4(" abc ") == " cba "

    print("All tests passed!")
