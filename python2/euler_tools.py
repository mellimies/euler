def is_prime(n):
    if n == 2 or n == 3: return True
    if n < 2 or n % 2 == 0: return False
    if n < 9: return True
    if n % 3 == 0: return False
    r = int(n ** 0.5)
    f = 5
    while f <= r:
        # print '\t',f
        if n % f == 0: return False
        if n % (f + 2) == 0: return False
        f += 6
    return True


def get_primes_up_to(limit, start=2):
    # return primes either up to specific value

    num_start = 3 if start == 2 else start

    nums = filter(is_prime, xrange(num_start, limit+1, 2))
    #print nums, start, num_start
    if start == 2:
        nums = [2] + nums
    return nums

def magic(x):
    if x < 10:
        return [x]
    else:
        return magic(x//10) + [x%10]

num_cache = dict()

def int2nums(num, cache=num_cache):
    if not num in num_cache:
        cache[num] = magic(num)
    #return magic(num)
    return cache[num]

def get_next_prime_up_from(num):
    if num < 2:
        return 2
    elif num == 2:
        return 3
    while True:
#        print "NUM", num
        num = num + 2 if num % 2 else num + 1
        if is_prime(num):
            return num
        num += 2

def factors(n):
    return set(reduce(list.__add__,
                ([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))

# Problem 102. Tried myself to find an algorigthm to check for point in triangle for a day... this is from SO

def sign(p1, p2, p3):
  return (p1[0] - p3[0]) * (p2[1] - p3[1]) - (p2[0] - p3[0]) * (p1[1] - p3[1])


def point_in_AABB(pt, c1, c2):
  return c2[0] <= pt[0] <= c1[0] and \
         c2[1] <= pt[1] <= c1[1]

def point_in_triangle(pt, v1, v2, v3):
  b1 = sign(pt, v1, v2) <= 0
  b2 = sign(pt, v2, v3) <= 0
  b3 = sign(pt, v3, v1) <= 0

  return ((b1 == b2) and (b2 == b3)) and \
         point_in_AABB(pt, map(max, v1, v2, v3), map(min, v1, v2, v3))

from math import sqrt, pow, log10
def oldF(n):
    return ((1+sqrt(5))**n-(1-sqrt(5))**n)/(2**n*sqrt(5))
    #return pow(2 << n, n + 1, (4 << 2*n) - (2 << n) - 1) % (2 << n)

def mul(a, b):
    return a[0] * b[1] + a[1] * b[0] + a[0] * b[0], a[0] * b[0] + a[1] * b[1]


def F(n):
    x, r = (1, 0), (0, 1)
    while n:
        if n & 1: r = mul(r, x)
        x = mul(x, x)
        n >>= 1
    return r[0]

def number_of_digits(num):
    return int(log10(num)) + 1

def first_n_digits(num, digits):
    return num / 10**(number_of_digits(num)-digits)

