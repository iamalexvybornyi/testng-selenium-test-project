# testng-selenium-test-project

This is a test automation project using Selenium, Java, TestNG, and Spring. It also has logging and Allure reports support.
TestNG's in-built features are used for running tests in parallel.

## How to run the tests
To start the tests using cmd just run the following command:
```sh
mvn clean test
```

To run the particular set of tests just create a new test suite file and use the `-Dsurefire.suiteXmlFiles` parameter. 
The command will look like the following:
```sh
 mvn clean test "-Dsurefire.suiteXmlFiles=./src/test/resources/ProductListTestSuite.xml"
```