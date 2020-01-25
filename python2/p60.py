# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=60
# Prime pair sets

# Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
# (129976621 ?)
from timeit import default_timer as timer
import itertools
from python2.euler_tools import is_prime, get_next_prime_up_from

t_start = timer()

rv = 0

def test_prime_pair(p):
    p1, p2 = p[0], p[1]
    return is_prime(int(str(p1) + str(p2)))

def test_prime_set(pset):
    #print "JEEE", list(itertools.permutations(pset, 2))
    pairs = list(itertools.permutations(pset, 2))
    return all(map(test_prime_pair, pairs))

start_primes = [3, 7, 109, 673]
print "SUM", sum(start_primes)
#start_primes = [3, 7, 109, 673, 129976621]
#print "SUM", sum(start_primes)

print "ON", test_prime_set(start_primes)
#raise SystemExit

s = set(itertools.permutations(start_primes, 2))

found = False
rv = None
#p = get_next_prime_up_from(start_primes[-1])
p = 2

while not found:
    test_set = start_primes + [p]
    print "P", p, test_set

    pairs = set(itertools.permutations(test_set, 2)) - s # don't check known good pairs
    found = True
    for pp in pairs:
        if not test_prime_set(pp):
            #print "NOOO", pp
            found = False
            break
    #print "PAIRS", len(pairs), len(set(pairs) - s)
#    if all(map(test_prime_set, pairs)):
#        found = True
    if found:
        print "JEEE", p, start_primes, pairs
        rv = test_set
#        break
    p = get_next_prime_up_from(p)
    if p > 129976621:
        break

print rv, sum(rv), timer() - t_start


raise SystemExit
primes = get_primes_up_to(675, 3)
print primes
nums = list(itertools.permutations(primes, 2))
#print nums
set1 = filter(test_prime_set, nums)

print "1", set1

set2 = set()
for pp in set1:
    for p in primes:
        ppp = sorted(pp + (p, ))
        if str(ppp) in set2:
            continue
        if test_prime_set(ppp):
            set2.add(str(ppp))

print "S2", set2

set3 = set()

for ppp in set2:
    for p in primes:
        pppp = eval(ppp)
        pppp.append(p)
        pppp = sorted(pppp)
        if str(pppp) in set3:
            continue

        if test_prime_set(pppp):
            #print "XXXXX", ppp, p
            set3.add(str(pppp))

print "S3", set3

for r in set3:
    r = eval(r)
    print r, all(map(is_prime, r)), sum(r)

print len(set3), timer() - t_start
