# Celsearch

We hope to allow people to text given numbers with queries so that they could access the Internet without an active connection. We also hope to incorporate state so that the users can make multiple queries about the same subject without explicitly stating it. We would also like to add a chat bot feature so that users could text the bot for friendly conversations.

How it works: 
The user would send a text message to a certain phone; an app on the receiving phone would detect the incoming message and send that query to our server which would then parse the results it gets back from searching the web and sends the result back to the receiving phone which would then send the result as a text message to the sender. 

The server would also have a database that would store state for each unique incoming number (the original sender) so that we can allow the user to make multiple queries about the same subject without explicitly stating it. 

This process could be crowd sourced by downloading the app on multiple phones. This process also allows us to bypass the use of traditional messaging APIs like Twilio. 

Feasability Study:
We think that this is feasible through Android's BroadcastReceiver class.  

# Requirements

- You need to have node and npm installed on your machine 
  - On macOS homebrew can be used to install node 
    - `brew install node`
- Run `npm install` to install all dependencies 
- You will also require mongodb 
  - Check [installation instructions](https://docs.mongodb.com/manual/installation/)
- You will need the wikipedia package 
  - `sudo pip install wikipedia`
- You will need nltk 
  - `sudo pip install -U nltk`
  - You will also need to download the punkt and average_perceptron_tagger 
    - Start a python shell, import nltk, and use `nltk.download()`

# How to run 

- Once you have all the requirements, simply go to the web/celsearch folder and use `npm run start` 
  - This will start the server 
- You can then go ahead and change the IP address used in the Android app in this [line](https://github.com/christopher18/Celsearch/blob/master/android/Celsearch/app/src/main/java/com/example/chris/celsearch/CelsearchReceiver.java#L81)
- Also make sure you have mongo running 
  - Use `mongod`; you might have to use `sudo mongod` if on a Linux machine
