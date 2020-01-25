# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=19
# Counting Sundays

# How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

from timeit import default_timer as timer

def is_leap(year):
    rv = False

    if year % 100 == 0:
        if year % 400 == 0:
            rv = True
    elif year % 4 == 0:
        rv = True

    return rv

dd = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

d_start = (1900, 1, 1) # Monday
wk_day = 1 # Monday (calendar use 0 for Mon)

years = range(1900, 2001)

c_sun = 0
counting = False

import calendar

for y in years:
    for m in range(1, 13):
        days = dd[m-1]
        if m == 2 and is_leap(y):
            days += 1

        for d in range(1, days + 1):
            if (1901, 1, 1) == (y, m, d):
                counting = True

            if counting and wk_day % 7 == 0 and d == 1:
                print "%d-%d-%d" % (y, m, d), is_leap(y), wk_day
                c_sun += 1
                assert wk_day == calendar.weekday(y, m, d) + 1

            wk_day += 1
            if wk_day % 8 == 0:
                wk_day = 1


t_start = timer()

print c_sun, timer() - t_start