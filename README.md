# Introduction
The ReviewManager is an application to handle architecture review results : 
* goals
* non-functional requirements
* architectural styles
* architecture decisions
* findings
* tradeoffs
* risks
* non-risks
* and todo's.

In future versions there will additional capabilities to visualize the results using D3.js / C3.js charts.

It was created as an Angular Grails application using lazybones. For more detailed information regarding the technology used see:
https://github.com/craigburke/lazybones-angular-grails

# Running the application
	gradlew run

# Running tests
	gradlew test

# Generate blank application
        lazybones generate module::blank

# Generate REST application
	* Create a domain class:
	gradlew grails-create-domain-class -PgrailsArgs=Foo

	* Generate your angular module:
	lazybones generate module
