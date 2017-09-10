# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=46
# Goldbach's other conjecture

# What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?

from timeit import default_timer as timer
from euler_tools import is_prime, get_primes_up_to

t_start = timer()

def get_prime_and_square(n):
    rv = None
    for prime in get_primes_up_to(n - 2):
        found = False
        diff = n - prime

        if diff % 2:
            continue

        ulimit = int((diff / 2) ** 0.5) + 1

        for sq in range(1, ulimit + 1):
            if prime + 2 * sq**2 == n:
                print "Found", n, prime, sq
                rv = (prime, sq)
                found = True
                break

        if found:
            break

    return rv

def update_num(n):
    n += 2
    while is_prime(n):
        return update_num(n)
    return n

num = 33
while True:
    #print num
    x = get_prime_and_square(num)
    if not x:
        rv = num, x
        print rv
        break
    num = update_num(num)

print rv, timer() - t_start
