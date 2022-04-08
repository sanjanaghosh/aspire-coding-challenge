# Aspire-QA-Development-Challenge

* **Author**: Sanjana Ghosh
* **Country**: India

## Assignment architecture
* The application build tool is Maven
* The Design pattern is Page Object Model

## Tools And Libraries
**Development Dependencies:**

* **Language Used**: Java | Version: 8
* **Test Framework Used**: TestNG | Version: 7.5
* **Automation Tool Used**: Selenium Webdriver | Version: 4.1.2
* **External Libraries Used**: 
  * AssertJ (for Test Result validation) | Version: 3.22 
  * Log4J (for Logger) | Version: 2.17.1
  * Java Faker (Random Data Generator) | Version: 1.0.2
* **Report Generator**: Allure | Version: 2.17 
* **Properties file Handler**: Owner | Version: 1.0.12

## Project Set-up on Local Machine

**Steps to follow**

1. Open IDE 
2. Navigate to **File** > Select **New** > Choose **Project from Version Control** option
3. Select **Repository URL** option
4. Select **Version Control** as Git, **URL** as  , and **Directory** as system path directory (as you want)
5. Click on **Clone** and wait for the conning to complete
6. The project is set up on your local system now

## Execution

1. Navigate to **src/test/resources/runner/** folder
2. Right-click on **test-runner.xml** file
3. Select **Run ...** Option from the list
4. Check the completion status in Console.

## Report Generation
1. Execute the Maven Goal:   `mvn allure:serve`  
2. Wait till the Allure zip file is downloaded from Maven central repo and a .allure folder is created at the project directory.
3. Check the console of the RUN and click on the link displayed to open the report.


