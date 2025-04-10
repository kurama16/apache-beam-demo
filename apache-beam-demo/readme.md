
# Apache Beam DEMO

This is a Demo to use Apache Beam. we have an example to call an API,
read as a String and then transformed into a DTO class. 
Lastly it format again to create a csv row and write into a file
***
## Requirements
* Java 17+
* Maven
* polygon.io API Key

to get an API key go to https://polygon.io/docs and signup or signin, then go to your dashborad and copy your default key.
***
## How to Use

to run this project you can use the following command as an example:

### Using PDirect runner with  Dummy API
``` Bash
mvn compile exec:java -Dexec.mainClass=org.nahu.ApacheBeamDemo -Pdirect-runner "-Dexec.args= --outputPath=src/main/resources/out --apiClient=DUMMY_CLIENT --url=https://api.polygon.io/v2 --stockTicker=AAPL --fromDate=2025-01-09 --toDate=2025-01-10 --apiKey=5uhhYyp2c2PwBIBK3mOwde14wTtb4cFQ"
```

### Using PDirect runner with Polygon API
``` Bash
mvn compile exec:java -Dexec.cleanupDaemonThreads=false -Dexec.mainClass=org.nahu.ApacheBeamDemo -Pdirect-runner "-Dexec.args= --outputPath=src/main/resources/out --apiClient=POLYGON_CLIENT --url=https://api.polygon.io/v2 --stockTicker=AAPL --fromDate=2025-01-09 --toDate=2025-01-10 --apiKey=5uhhYyp2c2PwBIBK3mOwde14wTtb4cFQ"
```

***
## Arguments
* outputPath: path where the csv will be created. By default, is src/main/resources/out
* url: URL used by the API client
* apiClient: required field. api client used in Apache Beam. At this moment you can select between DUMMY_CLIENT and POLYGON_CLIENT
* stockTicker: company symbol of the company to search. By default, is AAPL
* fromDate: start of the aggregate window. should be in format YYYYMMDD. by default is "2025-01-09"
* toDate: End of the aggregate window. should be in format YYYYMMDD. by default is "2025-02-10"
* apiKey: required field. it is the APIKey used to authenticate the api call

***
## Output

the output will be an .csv file located in the **outputPath** specify.
This file will have 7 variables and a header **(id, company_symbol, open_price, close_price, high_price, low_price, volume)**
with the stock information retrieve using the parameters sent