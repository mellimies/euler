# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=28
# Number spiral diagonals

# What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?

from timeit import default_timer as timer
import numpy as np

t_start = timer()

a_size = 1001
a = np.zeros((a_size, a_size))
row = col = a_size / 2
num = 0

while num < a_size**2:
    a[row, col] = num+1
    #print a
    if num == 0:
        col += 1
    else:
        #print row, col
        if (row, col) == (0, a_size - 1):
            break
        elif col > 0 and a[row, col-1] and a[row+1, col] == 0: # right -> down
            #print "-| down"
            row += 1
        elif col == 0 or a[row, col - 1] == 0 and a[row-1, col]:
            if col > 0:
                #print "<- left"
                col -= 1
            else:
                if row == 0:
                    col += 1
                    #print "^- up"
                else:
                    row -= 1
        elif a[row-1, col] == 0 and (a[row-1, col+1] or a[row, col+1]):
            #print "^- up"
            row -= 1
        elif (row, col) == (0, 0) or a[row, col+1] == 0 and (a[row+1, col]):
            #print "-> right"
            col += 1

    num += 1

rv = 0

diag = range(a_size)
for i in diag:
    rv += a[i, i]

diag2 = range(a_size) # a copy from diag would also do
diag2.reverse()

#print a #diag, diag2, zip(diag2, diag)
for r,c in zip(diag2, diag):
    rv += a[r,c]

rv -= 1 # don't count center twice
print rv, timer() - t_start