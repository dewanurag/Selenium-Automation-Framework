# Automation Practice — UI Test Framework

A production-grade Selenium 4 + TestNG UI automation framework for [automationpractice.techwithjatin.com](https://automationpractice.techwithjatin.com), built with Java. Supports data-driven testing, parallel execution, cloud grid testing via LambdaTest, and rich HTML reporting.

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17+ | Core language |
| Selenium WebDriver | 4.41.0 | Browser automation |
| TestNG | 7.4.0 | Test execution and management |
| ExtentReports | 5.1.2 | HTML test reporting |
| Log4j2 | 2.25.4 | Structured logging |
| Jackson Databind | 2.15.2 | JSON deserialization to POJO |
| Apache POI | 5.4.1 | Excel data reading (.xlsx) |
| OpenCSV | 5.7.1 | CSV data reading |
| Maven Surefire | 3.2.5 | Test execution via Maven |
| LambdaTest | — | Cloud cross-browser grid |

---

## Project Structure

```
automationPractice/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           ├── constants/
│   │           │   ├── Browsers.java          # Browser enum (CHROME, EDGE)
│   │           │   └── Environments.java      # Environment enum (QA, STAGING)
│   │           ├── pojo/
│   │           │   ├── User.java              # Test data POJO
│   │           │   └── TestData.java          # Root wrapper POJO for JSON
│   │           └── utility/
│   │               ├── BrowserUtility.java    # Abstract base — driver, waits, actions
│   │               ├── LoggerUtility.java     # Log4j2 logger factory
│   │               ├── ExtentReporterUtility.java  # ThreadLocal ExtentReports manager
│   │               └── PropertiesUtil.java    # Config/properties reader
│   └── test/
│       └── java/
│           └── com/
│               └── ui/
│                   ├── base/
│                   │   └── BaseTest.java      # @BeforeMethod/@AfterMethod setup
│                   ├── dataproviders/
│                   │   └── LoginDataProvider.java  # JSON/Excel/CSV data providers
│                   ├── listeners/
│                   │   ├── TestListener.java        # ITestListener — reports + screenshots
│                   │   ├── AnnotationTransformer.java  # Injects RetryAnalyser globally
│                   │   └── RetryAnalyser.java       # IRetryAnalyzer — retry on failure
│                   ├── pages/
│                   │   ├── HomePage.java
│                   │   └── LoginPage.java
│                   └── tests/
│                       └── LoginTest.java
├── config/
│   └── config.properties                     # URL, credentials, environment config
├── test-data/
│   └── loginData.json                        # Test data files
├── src/main/resources/
│   └── log4j2.xml                            # Logging configuration
├── test-output/
│   └── screenshots/                          # Auto-captured on failure
├── logs/
│   └── automation.log                        # Log file output
├── testng.xml                                # Suite configuration
├── pom.xml
└── README.md
```

---

## Key Features

### Page Object Model
Abstract `BrowserUtility` class provides all WebDriver interactions — `clickOn()`, `enterText()`, `getVisibleText()` — with built-in explicit waits. Page classes extend it and expose business-level methods.

### ThreadLocal WebDriver
`WebDriver` and `WebDriverWait` are stored in `ThreadLocal` variables, making every thread's driver instance completely isolated. Safe for parallel test execution with no shared state.

### Data-Driven Testing
Three data source formats supported via TestNG `@DataProvider`:

- **JSON** — deserialized to POJO using Jackson `ObjectMapper`
- **Excel (.xlsx)** — read using Apache POI `XSSFWorkbook`
- **CSV** — read using OpenCSV

### Retry Mechanism
`RetryAnalyser` implements `IRetryAnalyzer` and is injected into every `@Test` method automatically via `AnnotationTransformer` (`IAnnotationTransformer`) — no `retryAnalyzer =` annotation needed on individual tests.

### Listeners
- `TestListener` — implements `ITestListener`, creates ExtentTest per method, logs pass/skip/fail, and embeds screenshots on failure
- `AnnotationTransformer` — globally injects retry analyser at runtime

### Screenshot on Failure
Screenshots are captured in `TestListener.onTestFailure()`, saved to `test-output/screenshots/` with format `testName-dd-MMM-yyyy-HH-mm-ss.png`, and embedded directly into the ExtentReport.

### ExtentReports
Thread-safe `ExtentReporterUtility` uses a `ThreadLocal<ExtentTest>` to ensure each parallel thread logs to its own test node. Report is flushed in `onFinish()`.

### Log4j2 Logging
`LoggerUtility.getLogger(Class)` provides a named logger per class. Logs go to both console and `logs/automation.log`. Log level is configurable in `log4j2.xml`.

### LambdaTest Integration
Pass `-DisLambdaTest=true` to route WebDriver to LambdaTest RemoteWebDriver. Capabilities are configured via `config.properties`.

### Headless Mode
Pass `-DisHeadless=true` to run Chrome or Edge in headless mode — useful for CI/CD pipelines where no display is available.

---

## Setup and Installation

### Prerequisites
- Java 17+
- Maven 3.8+
- Chrome or Edge browser installed
- ChromeDriver / EdgeDriver matching your browser version (or use WebDriverManager)

### Clone and Install

```bash
git clone https://github.com/yourusername/automationPractice.git
cd automationPractice
mvn clean install -DskipTests
```

---

## Running Tests

### Run all tests (default — Chrome, non-headless, local)
```bash
mvn test
```

### Run in headless mode
```bash
mvn test -DisHeadless=true
```

### Run on Edge
```bash
mvn test -Dbrowser=edge
```

### Run on LambdaTest
```bash
mvn test -DisLambdaTest=true
```

### Run with all overrides
```bash
mvn test -Dbrowser=chrome -DisHeadless=true -DisLambdaTest=false
```

### Default property values (defined in `pom.xml <properties>`)

| Property | Default |
|---|---|
| `browser` | `chrome` |
| `isHeadless` | `false` |
| `isLambdaTest` | `false` |

---

## Configuration

### `config.properties`
```properties
URL.QA=https://automationpractice.techwithjatin.com/
URL.STAGING=https://staging.automationpractice.techwithjatin.com/

LT_USERNAME=your_lambdatest_username
LT_ACCESS_KEY=your_lambdatest_access_key
LT_GRID_URL=hub.lambdatest.com/wd/hub
```

### `log4j2.xml` — change level for more/less output
```xml
<Root level="info">   <!-- change to debug for verbose output -->
```

### `testng.xml` — control parallelism
```xml
<test thread-count="3" parallel="methods">
```

---

## Test Reports

After execution, open the ExtentReport:

```
test-output/ExtentReport.html
```

Screenshots of failed tests are embedded directly in the report and also saved to:

```
test-output/screenshots/testName-dd-MMM-yyyy-HH-mm-ss.png
```

---

## Logging

Logs are written to both console and file during every run:

```
logs/automation.log
```

Log levels used across the framework:

| Level | Usage |
|---|---|
| `INFO` | Normal flow — browser launch, navigation, clicks |
| `WARN` | Retries, fallbacks, unexpected but recoverable |
| `ERROR` | Failures, exceptions caught in catch blocks |
| `DEBUG` | Verbose diagnostic info during development |

---

## Author

**Anurag Dewangan**  
Senior SDET | Infosys Ltd  
[LinkedIn](https://linkedin.com/in/yourprofile) • [GitHub](https://github.com/yourusername) • [@dewanurag](https://instagram.com/dewanurag)
