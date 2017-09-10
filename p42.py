# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=42
# Coded triangle numbers

# Using words.txt, a 16K text file containing nearly two-thousand common English words, how many are triangle words?

from timeit import default_timer as timer

t_start = timer()

def get_triangle_number(n):
    return n * (n + 1) / 2

t_nums = [get_triangle_number(1)]

def is_triangle(num):
    if num > t_nums[-1]:
        t_nums.append(get_triangle_number(len(t_nums) + 1))
        return is_triangle(num)
    return num in t_nums

with open('p042_words.txt') as f:
    lines = f.read().replace('"', '').split(',')

rv = 0

for l in lines:
    l_sum = sum(map(lambda x: ord(x)-64, list(l)))
    if is_triangle(l_sum):
        rv += 1

print rv, timer() - t_start
