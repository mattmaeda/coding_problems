import unittest
from exact_change import exact_change


class TestExactChange(unittest.TestCase):

    def setUp(self):
        pass


    def test_modulo_equals_ones(self):
        self.assertEqual(exact_change(3, 1, 8), True)


    def test_modulo_greater_than_ones(self):
        self.assertEqual(exact_change(3, 1, 9), False)


    def test_modulo_less_than_ones(self):
        self.assertEqual(exact_change(4, 1, 8), True)


    def test_diff_equals_ones(self):
        self.assertEqual(exact_change(5, 2, 15), True)


    def test_diff_greater_than_ones(self):
        self.assertEqual(exact_change(5, 2, 16), False)


    def test_diff_less_than_ones(self):
        self.assertEqual(exact_change(7, 2, 16), True)


    def test_no_ones_modulo_zero(self):
        self.assertEqual(exact_change(0, 5, 10), True)


    def test_no_ones_module_greater_than_zero(self):
        self.assertEqual(exact_change(0, 5, 11), False)


    def test_no_fives_amount_equal_to_ones(self):
        self.assertEqual(exact_change(5, 0, 5), True)


    def test_no_fives_amount_greater_than_ones(self):
        self.assertEqual(exact_change(14, 0, 15), False)


    def test_no_fives_amount_less_than_ones(self):
        self.assertEqual(exact_change(1000, 0, 999), True)


    def test_zero_amount(self):
        self.assertEqual(exact_change(5, 1, 0), True)


if __name__ == '__main__':
    unittest.main()
