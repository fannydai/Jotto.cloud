# This program will filter out words in the temp_words.txt
# file so all words are valid
# 5 letter. all unique letter. no duplicate words
import re

def unique(word):
    lst = []
    for i in word:
        if i in lst or (not re.match("^[A-Za-z]*$", i)):
            return False
        else:
            lst.append(i)
    return True

def filter():
    lst = []
    file_path = "./dictionary.txt"
    file = open(file_path, "r")

    for line in file:
        line = line.replace("\n","").upper()
        if len(line)==5 and (line not in lst) and unique(line):
            lst.append(line.replace('\n',''))

    lst = sorted(lst)

    file = open("dictionary.txt","w")
    for i in lst:
        file.write(i + "\n")
    file.close()

filter()