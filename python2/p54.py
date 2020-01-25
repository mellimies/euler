# -*- coding: utf-8 -*-

# https://projecteuler.net/problem=54
# Poker hands

# How many hands does Player 1 win?

from timeit import default_timer as timer
from collections import Counter

t_start = timer()


rv = 0

D = {'T': 10, 'J': 11, 'Q': 12, 'K': 13, 'A': 14}

def get_cards(hand):
    rv = set()
    for c in hand:
        num, suit = list(c)
        num = int(num) if not num in D else D[num]
        rv.add((num, suit))
    return rv

def is_straight(nums):
    return sum(nums) == 5 * sorted(nums)[0] + 10

def check_hand(hand_str):
    cards = get_cards(hand_str)
    nums = sorted(map(lambda x: x[0], cards))
    suits = map(lambda x: x[1], cards)
    cnums = sorted(Counter(nums).values())

    rv = [0]

    if is_straight(nums) and nums[0] == 10 and len(set(suits)) == 1: # Royal Flush
        return [1000]
        pass
        #print "Royal Flush"
    elif is_straight(nums) and len(set(suits)) == 1: # Straight Flush
        return [900]
        pass
        #print "Straight Flush"
    elif cnums == [1, 4]: # Four of a Kind
        rv = [4 * max(cards)[0], min(cards)[0]]
        #print "4", cards, rv
        return rv
        pass
        #print "Four of a Kind"
    elif cnums == [2, 3]: # Full House
        d = Counter(nums)
        k1, k2 = d.keys()
        rv = [3 * k1, 2 * k2] if d[k1] > d[k2] else [3 * k2, 2 * k1]
        print "Full House", cards, rv
        return rv
        pass
        #print "Full House"
    elif len(set(suits)) == 1:
        pass
        #print "Flush"
    elif is_straight(nums):
        pass
        #print "Straight"
    elif cnums == [1, 1, 3]:
        pass
        #print "Three of a Kind"
    elif cnums == [1, 2, 2]:
        pass
        #print "Two Pairs"
    elif cnums == [1, 1, 1, 2]:
        pass
        #print "One Pair"
    elif cnums == [1, 1, 1, 1, 1]:
        pass
        #print "High Card"
    else:
        print "Unknown", cards, Counter(nums).values()

    return rv

#check_hand("TH JH QH KH AH".split(" ")) # Royal Flush
#check_hand("1H 2H 3H 4H 5H".split(" ")) # Straight Flush
check_hand("AD 9C AS AH AC".split(" "))
#check_hand("1H JH 4H KH AH".split(" ")) # Flush

with open('p054_poker.txt') as f:
    plays =  f.read().splitlines()

for p in plays:
    f = p.split(' ')
    h1 = f[0:5]
    h2 = f[5:]
    #print h1
    check_hand(h1)
    check_hand(h2)

print rv, timer() - t_start
