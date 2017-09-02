# https://projecteuler.net/problem=1

limit = 1000
numbers = xrange(limit)
rv = sum(filter(lambda x: x % 3 == 0 or x % 5 == 0, numbers)) # 233168
print rv