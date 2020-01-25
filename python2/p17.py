# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=17
# Number letter counts

# If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
# If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?

from timeit import default_timer as timer
t_start = timer()

d = {
    1 : 'one',
    2 : 'two',
    3 : 'three',
    4 : 'four',
    5 : 'five',
    6 : 'six',
    7 : 'seven',
    8 : 'eight',
    9 : 'nine',
    10 : 'ten',
    11 : 'eleven',
    12 : 'twelve',
    13 : 'thirteen',
    14 : 'fourteen',
    15 : 'fifteen',
    16 : 'sixteen',
    17 : 'seventeen',
    18 : 'eighteen',
    19 : 'nineteen',
    20 : 'twenty',
    30 : 'thirty',
    40 : 'forty',
    50 : 'fifty',
    60 : 'sixty',
    70 : 'seventy',
    80 : 'eighty',
    90 : 'ninety'
}

def roundUpToMultiple(number, multiple):
    num = number + (multiple - 1)
    return num - (num % multiple)

def num2str(num):

    rv = d.get(num)
    if not rv:
        f = map(int, list(str(num)))
        print f

        n_tens = f[-2]
        tens = d.get(n_tens)

        ones = d.get(f[-1])
        hundreds = ''
        if len(f) == 3:
            n_hundreds = f[-3]
            hundreds = "%s hundred" % d.get(n_hundreds)

            print f, f[-3]

            if num % (n_hundreds * 100) == 0: # even hundred
                tens = ''
                ones = ''
            else:
                hundreds = "%s and " % hundreds

        if n_tens > 0 and num % (n_tens * 10) == 0: # even ten
            tens = d.get(n_tens)
            ones = ''

        print hundreds, tens, ones
    return rv

limit = 125
numbers = range(1, limit + 1)
for num in numbers:
    print num, num2str(num)
rv = 1
print rv, timer() - t_start