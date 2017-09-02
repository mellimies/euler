# https://projecteuler.net/problem=7
# 10001st prime

# By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
# What is the 10 001st prime number?

def is_prime(n):
  if n == 2 or n == 3: return True
  if n < 2 or n % 2 == 0: return False
  if n < 9: return True
  if n % 3 == 0: return False
  r = int(n**0.5)
  f = 5
  while f <= r:
    #print '\t',f
    if n % f == 0: return False
    if n % (f+2) == 0: return False
    f += 6
  return True

def fail_is_prime(number): # https://stackoverflow.com/questions/15285534/isprime-function-for-python-language
    return 2 in [number, pow(2,number,number)] # claims 341 is a prime when it's not

from timeit import default_timer as timer
t_start = timer()

count = 0
target = 10001

i = 2

while True:
    if is_prime(i):
        count += 1
        # print i, is_prime(i), count
        if count == target:
            rv = i
            break

    i += 1

print rv, count, timer() - t_start