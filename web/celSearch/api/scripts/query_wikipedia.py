''' 
Script used to query Wikipedia for summary of object 
'''

import sys
import wikipedia 
import nltk 

def main():
	# Check that we have the right number of arguments 
	if (len(sys.argv) != 2): 
		print 'Incorrect number of arguments; please pass in only one string that contains the query'
	 	return 'Banana'

	# Get the noun from the query (uses the first noun it finds for now)
	print sys.argv[0]
	tokens = nltk.word_tokenize(sys.argv[1])
	tagged = nltk.pos_tag(tokens)

	# Find first noun in query and provide Wikipedia summary for it 
	for tag in tagged: 
		if tag[1][0] == 'N': 
			print wikipedia.summary(tag[0])
			return 


if __name__ == '__main__':
	main()