Image Manipulation and Enhancement Ultra Version

Using the JAR file:

The jar file can be used in 3 different ways:

1. For using the application in GUI mode, use the command java -jar Assignment6.jar
2. For using the application in interactive mode, use the command java -jar Assignment6.jar -text
3. For using the application to execute a script file, use the command java -jar Assignment6.jar -file filepath

For guidance on how to use the GUI, please refer to the USEME file.

New additions in this version(Ultra):

1. In this version a new GUI has been introduced that the user can use and perform all the operations that were developed in the previous versions. A screenshot has been provoded in the "res" folder to show the same.

2. A new UI package has been added, where all the GUI components reside.

3. A new interface UIFeatures has been added which has been implemented by the IMEControllerUltraImpl class which contains callback methods that are used by GUI components based on certain events/actions.

4. All the asked functionalities have been implemented as asked on the GUI and also proper messages are shown to the user in case any errors occur.
 

Design Changes and Justifications

There were no design changes done as such. Some classes in the "model" package were made package private, so that the view and the entities in the controller and view packages do not know about the underlying image models. All the new functionalities were added using the existing interfaces and classes


NOTE: For running the unit test cases, please unzip the "ExpectedPixels" and "images" folder inside the test-res folder.


Citation for the sample image used
URL: https://burst.shopify.com/photos/pet-and-owner-on-fall-walk?c=fall
Photo By: Matthew Henry
License: https://burst.shopify.com/licenses/shopify-some-rights-reserved

