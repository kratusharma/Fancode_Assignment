# FanCode Todo Test Automation

This project automates the verification of whether users from the "FanCode" city have more than half of their todo tasks completed.

## Project Structure

src
├── main
│ └── java
│ └── (no files)
├── test
│ └── java
│ ├── FanCodeTodoTest.java
│ ├── User.java
│ ├── Address.java
│ ├── Geo.java
│ └── Todo.java
testng.xml
pom.xml
README.md



## Setup and Running Tests

1. **Clone the Repository:**
    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Install Dependencies:**
   Ensure you have Maven installed, then run:
    ```bash
    mvn clean install
    ```

3. **Run the Tests:**
    ```bash
    mvn test
    ```

## Dependencies
RestAssured
TestNG


## Model Classes
User.java
Address.java
Geo.java
Todo.java
These classes represent the JSON responses from the API.




