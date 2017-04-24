##Description

The task is about Decathlon competition.
<br>
 - [X] The input of the Java program is a CSV-like text file (see the attachment). The task is to output an XML file with all athletes in ascending order of their places, containing all the input data plus total score and the place in the competition (in case of equal scores, athletes must share the places, e.g. 3-4 and 3-4 instead of 3 and 4). Input and output file names should be provided as parameters to the Java application at the startup. Application should ask for those parameters itself.
 - [X] It would be great if an XSL file for viewing the produced XML nicely using a web browser is also provided.
 - [X] Be sure to keep the code design simple, but allowing to easily change or add more input sources and/or output file formats.
 - [X] Unit tests for the code are mandatory.
 - [X] No external libraries are allowed in addition to the Java standard API except JUnit**.
 - [X] Gradle should be used as building tool.
 - [X] Recommended java version is 7, but not mandatory.
 - [X] Please zip your code together with the project file. Keep in mind that we are going to run both your program and the tests.
 - [X] It is preferred that IntelliJ is used as IDEA.
<br>

Some links:

 * http://en.wikipedia.org/wiki/Decathlon (see formulas are at the end of the page)
 * http://www.junit.org/ - JUnit
 * http://www.jetbrains.com/ - IntelliJ IDEA download

Try to keep your code as readable as possible. We value code simplicity. Use object-oriented approach with design patterns where applicable.

##Usage

To run application:<br>
```gradlew run```

To tun test:<br>
```gradlew test```
