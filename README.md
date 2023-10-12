# IME
Java and Swing based MVC application that offers image manipulation and enhancement features.

This is a purely Java based application that uses MVC architecture and other best practices where users can perform image manipulating and enhancing features.
This application can be run either through a CLI or a GUI.

For running the application in CLI mode, you can create a jar and run the jar using the following command just like any other Java application:
- command java -jar Assignment6.jar {operation}

where *operation is IME operation you want to perform. For a full list of operations please check the USEME.txt file inside the IME folder. If you have a set of operations that
you want to perform one after the another, then you can use the following command:
- command java -jar Assignment6.jar -file {fileName}

where *fileName is the text file where you have mentioned the operations. Each line of the file should have one operation.

If you want to run the application in GUI mode, then you can do so by just double clicking on the jar.
