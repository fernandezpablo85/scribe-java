# Report for Assignment 1

## Project chosen
Name: cribeJava
URL: https://github.com/scribejava/scribejava
Number of lines of code and tool used to count it: 17332, lizard
Programming language: Java

## Coverage measurement
### Existing tool
Name of the existing tool used was Jacoco. It was executed by adding a Jacoco plugin into the pom.xml file and then executing maven. (Add some details)
(Provide screenshot, there is a problem that it only creates reports for specific parts such as scribejava-core, figure out a way to put it all in one report)
![ScreenShot of coverage results](image.png)

### Our own coverage tool
Tomas Busa
Function 1: com.github.scribejava.httpclient.apache.ApacheHttpClient.getRequestBuilder

![Data Structure to hold coverage information, write all information about the branches taken to a console](image-1.png)
![Set a flag if the branch is reached](image-2.png)
![Coverage results output](image-3.png)

Function 2: com.github.scribejava.httpclient.apache.OAuthAsyncCompletionHandler.completed
![Data Structure to hold coverage information, write all information about the branches taken to a console](image-4.png)
![Set a flag if the branch is reached](image-5.png)
![Coverage results output](image-6.png)

![Before adding coverage](image.png)
![After adding coverage](image-1.png)

Jayran Duggins
Function 1: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getHttpMethod)

Function 2: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaWebClientBuilder.java (newWebClient)



Nikola Bakalinov
Function 1: scribejava/scribejava-httpclient-ning/src/main/java/com/github/scribejava/httpclient/ning
/NingHttpClient.java  (doExecuteAsync)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/af7aefc7-8db2-422b-8420-7760e43b8191)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/f45a7c09-47b2-4142-9d92-223d0b758001)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cda0b2e0-cef0-499d-a484-cdcdab1d1985)










