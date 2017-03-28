# Celsearch

We hope to allow people to text given numbers with queries so that they could access the Internet without an active connection. We also hope to incorporate state so that the users can make multiple queries about the same subject without explicitly stating it. We would also like to add a chat bot feature so that users could text the bot for friendly conversations.

How it works: 
The user would send a text message to a certain phone; an app on the receiving phone would detect the incoming message and send that query to our server which would then parse the results it gets back from searching the web and sends the result back to the receiving phone which would then send the result as a text message to the sender. 

The server would also have a database that would store state for each unique incoming number (the original sender) so that we can allow the user to make multiple queries about the same subject without explicitly stating it. 

This process could be crowd sourced by downloading the app on multiple phones. This process also allows us to bypass the use of traditional messaging APIs like Twilio. 

Feasability Study:
We think that this is feasible through Android's BroadcastReceiver class.  
