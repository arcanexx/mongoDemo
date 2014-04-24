mongoDemo
=========

mongoDemo using mjorm ORM for OBB

A few design notes to help you understand my thought process!

As you remember, I don't have experience with MongoDB or ORM.  This is my HelloWorld application, so hopefully I haven't gone completely off the rails on design strategy.

I've found something called MJORM (mongo-java-omm) for mapping tools for Java and Mongo. It seems to do the trick well and I really like how easy it is to create the java objects.  The documentation for queries is less clear.  It's a google project that can be included with Maven here:

http://code.google.com/p/mongo-java-orm/

I tried to create lots of unit test cases for the java classes themselves, as well as some database validations.  The getter/setter unit tests for the objects are academic, but it's meant to indicate I thought about it.

I wish I had some more time to cover some more operations, but just very busy with trying to close down the GoDaddy office so I hope this gives you an indication of my coding style and OO architecture decisions.

Please let me know if you would like to critique the design, there are already a few places I'd improve at next pass, but wanted to make sure I got you the 48 hour turn around as requested.

I enjoyed poking around with the various ORM tools and think this is a very good exercise, so thanks for the chance to learn a thing or two!
