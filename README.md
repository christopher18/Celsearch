# Celsearch

Celsearch allows people to text given numbers with queries so that they can access the Internet without using typical protocols such as Wi-Fi or LTE. The user can send a text message to a certain phone; an app on the receiving phone detects the incoming message and sends that query to our server, which then parses the results it gets back from searching the web and sends those results back to the receiving phone. The receiving phone formats the results into a text message, which it then sends back to the initial device.  Currently the project supports querying wikipedia and mitsuku.  If the user wishes to look up a wikipedia page on a certain subject, they can text "@wiki <subject>" to the given number.  If the user wishes to speak with the mitsuku chatbot for a friendly conversation then they can text "@mitsuku <message>" to the given number.

In the future, we hope to incorporate state so that the users can make multiple queries about the same subject without explicitly stating it. 

We hope that in the future this process could be crowd sourced by downloading the app on multiple phones to help provide a free internet to everyone. 

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
- You can then go ahead and change the IP address used in the Android app in this [line](https://github.com/christopher18/Celsearch/blob/master/android/Celsearch/app/src/main/java/com/example/chris/celsearch/CelsearchRestClient.java#L8)
- Also make sure you have mongo running 
  - Use `mongod`; you might have to use `sudo mongod` if on a Linux machine
