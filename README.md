# Introduction

This is a very simple Spring Boot app which is a demo for integration with [Sonar Cloud](https://sonarcloud.io/).
The analysis is CI based and not automatic. SonnarScanner for Gradle was used.

The results of the code analysis and the code coverage are uploaded [here](https://sonarcloud.io/dashboard?id=luchob_sonarcloud).

# Configuration

The JaCoCo and the Sonar scanner are imported (please check for newer version of the Sonarqube plugin):

```
plugins {
	id 'jacoco'
	id 'org.sonarqube' version "3.0"
}
```

The minimum required properties for sonar are configured:

```
sonarqube {
	properties {
		property "sonar.projectKey", "luchob_sonarcloud"
		property "sonar.organization", "luchob"
		property "sonar.host.url", "https://sonarcloud.io"
	}
}
```

| Property | Explanation |
| -------- | ----------- |
| `sonar.projectKey`   | Unique key specific for the project. |
| `sonar.organization` | The GitHub organization. Every project should be bound to an organization. |
| `sonar.host.url`     | Should be `https://sonarcloud.io`. |

The binary JaCoCo reports are deprecated for Sonar analysis thus XML reports need to be enabled.

```
jacocoTestReport {
	reports {
		xml.enabled true
	}
}
```

# Running the analysis

Every project gets a secret token so that the analysis results can be uploaded to SonarCloud.
The token can be used as an environment variable with name `SONAR_TOKEN`.
E.g. before running the analysis on Mac export the environment variable like this:

`export SONAR_TOKEN <your-token-here>`

Finally for gralde run the analysis:

```
./gradlew clean build jacocoTestReport sonarqube
```

The task `jacocoTestReport` should be run otherwise no code coverage data will be exported.
More details [here](https://community.sonarsource.com/t/coverage-test-data-importing-jacoco-coverage-report-in-xml-format/12151).
