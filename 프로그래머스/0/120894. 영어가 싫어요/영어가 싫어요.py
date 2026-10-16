def solution(numbers):
    words = ['zero', 'one', 'two', 'three', 'four',
             'five', 'six', 'seven', 'eight', 'nine']

    for num, word in enumerate(words):
        numbers = numbers.replace(word, str(num))
    return int(numbers)