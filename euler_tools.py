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

    num_start = 3 if start == 2 else start

    nums = filter(is_prime, xrange(num_start, limit+1, 2))
    #print nums, start, num_start
    if start == 2:
        nums = [2] + nums
    return nums

def int2nums(num):
    return map(int, list(str(num)))

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
