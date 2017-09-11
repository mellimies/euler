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


def get_primes_up_to(limit, start = 2):
    # return primes either up to specific value

    nums = filter(is_prime, xrange(start, limit+1, 2))
    if start == 2:
        nums = [2] + nums
    return nums

def int2nums(num):
    return map(int, list(str(num)))
