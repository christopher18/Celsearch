# utf-8 encoding needed since it's used for the bot
# -*- coding: utf-8 -*-

import sys
import re
import requests
from bs4 import BeautifulSoup

# URL for the chatbot
URL = "https://kakko.pandorabots.com/pandora/talk?botid=f6a012073e345a08&skin=chat"

# Regex pattern to get the appropriate data 
PATTERN = re.compile("</b>((.|\n)*?)<br>")

def ask_mitsuku(message): 	
	# Payload with message to POST 
	payload = {
		'message': message
	}
	session = requests.session()

	# Make POST request 
	r = requests.post(URL, data=payload)

	# Parse data for Mitsuku's response
	soup = BeautifulSoup(r.content, 'html.parser')
	content = str(soup.p)
	pat = re.findall(PATTERN, content)

	# Return the appropriate response 
	return pat[1][0]


def main(): 
	# Check that we have the right number of arguments 
	if (len(sys.argv) != 2): 
		print 'Incorrect number of arguments; please pass in only one string that contains the query'
		exit()

	print ask_mitsuku(sys.argv[1])

# Execute main
if __name__ == '__main__':
	main()
