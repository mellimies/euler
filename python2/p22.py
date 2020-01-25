# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=22
# Names scores

# 938 COLIN [67, 79, 76, 73, 78]
from timeit import default_timer as timer

t_start = timer()

with open('p022_names.txt') as f:
    names = sorted(f.readlines()[0].split(","))

names = map(lambda x: x.replace('"', ''), names)
rv = 0

for i, name in enumerate(names, 1):
    rv += i * sum(map(lambda x: ord(x) - 64, list(name)))
    #print i, name, map(lambda x: ord(x) - 64, list(name)), sum(map(lambda x: ord(x) - 64, list(name)))
print rv, timer() - t_start