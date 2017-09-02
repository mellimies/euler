# https://projecteuler.net/problem=4
# Largest palindrome product

# 9009 = 91 * 99

num_start = 999
num_test = num_start ** 2
test_range = xrange(num_start, 1, -1)

rv = None
while True and num_test >= 0 and not rv:
    num_test -= 1
    if str(num_test) != str(num_test)[::-1]: # palindrome test
        continue

    #print num_test

    for i in test_range:
        if num_test % i == 0 and num_test / i <= num_start:
            print num_test, i, num_test / i
            rv = num_test
            break

print num_test
