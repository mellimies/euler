# https://projecteuler.net/problem=2
# Even Fibonacci numbers

from timeit import default_timer as timer

t_start = timer()

limit = 4000000
low, high = 0, 1

rv = 0
while True:
    new_high = low + high

    if new_high >= limit:
        break

    if new_high % 2 == 0:
        rv += new_high

    low, high = high, new_high

print rv  # 4613732
print rv, timer() - t_start
