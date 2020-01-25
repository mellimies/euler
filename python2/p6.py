# https://projecteuler.net/problem=6
# Sum square difference

# The sum of the squares of the first ten natural numbers is 385. The square of the sum of the first ten natural
# numbers is 3025. Hence the difference between the sum of the squares of the first ten natural numbers and the
# square of the sum is # 3025-385 = 2640. Find the difference between the sum of the squares of the first
# one hundred natural numbers and the square of the sum.

from timeit import default_timer as timer
t_start = timer()

limit = 100
test_nums = range(1, limit + 1)

a = sum(map(lambda x: x**2, test_nums))
b = reduce(lambda x, y: x+y, test_nums)**2

rv = b-a

print rv, timer() - t_start