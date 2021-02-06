# proapps-mobile-framework

Make sure that appium is running on your machine and the correct appium server url is given in the commonConfig.properties file present in src/test/resources folder.

Set the right android related parameters in androidConfig.properties file present in src/test/resources folder to be able to run the test cases in the right emulator installed on your machine.
Make sure that the emulator the scenarios are tried to run is already installed in the system running the appium server. 

To run the scenarios via maven, just clone the repository and run the following command via terminal : 
mvn clean install

To run the scenarios using tags, use the following command : 
mvn clean install -Dcucumber.filter.tags = "@yourtagname"
