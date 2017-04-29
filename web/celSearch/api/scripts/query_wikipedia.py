''' 
Script used to query Wikipedia for summary of object 
'''

import sys
import wikipedia 

def main():
	# Check that we have the right number of arguments 
	if (len(sys.argv) != 2): 
		print 'Incorrect number of arguments; please pass in only one string that contains the subject'
	 	return 'Banana'

	print wikipedia.summary(sys.argv[1]).encode('utf-8')
	#return wikipedia.summary(sys.argv[1])


if __name__ == '__main__':
	main()
