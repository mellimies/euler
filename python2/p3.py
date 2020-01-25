# https://projecteuler.net/problem=3
# Largest prime factor

#num = 13195 # prime factors: 5, 7, 13 and 29
num = 600851475143

def is_prime(number): # https://stackoverflow.com/questions/15285534/isprime-function-for-python-language
    return 2 in [number, 2 ** number % number]

import math
start = int(math.sqrt(num))
if start % 2 == 0:
    start += 1

from timeit import default_timer as timer

t_start = timer()
while True:
    #print start, is_prime(start), num % start == 0
    if not start % 3 == 0 and is_prime(start) and num % start == 0:
        print start
        break

    start -= 2
print timer() - t_start