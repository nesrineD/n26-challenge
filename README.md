# n26-challenge
The aim of this application is to provide a restful api for n26 statistics.
### Build the Application:
mvn clean install
### Run the Application
mvn spring-boot:run
### Specs
The Rest Client HTTPRequester was used for running the HTTP Requests
#### Transactions:

* Add a transaction ( with timestamp in the last minute): 

##### Request 

```
POST http://localhost:8080/transactions
Content-Type: application/json
{
"amount": 10.0,
"timestamp" : 1508927852531
}```

##### Response 

``` 201 
Content-Length:  0
Date:  Wed, 25 Oct 2017 10:37:42 GMT
```

* Add a transaction ( with an old timestamp ): 

##### Request 

``` 
POST http://localhost:8080/transactions
Content-Type: application/json
{
"amount": 10.0,
"timestamp" : 1508927852531
}```

##### Response 

``` 204 
Date:  Wed, 25 Oct 2017 10:39:59 GMT
```

#### Statistics:
It returns the statistic based on the transactions which happened in the last 60 seconds:

##### Request 
``` 
GET http://localhost:8080/statistics
```
##### Response 

``` 200 
Content-Type:  application/json;charset=UTF-8
Date:  Wed, 25 Oct 2017 10:42:26 GMT
Transfer-Encoding:  chunked
{ "sum":30.0,
  "avg":10.0,
  "max":10.0,
  "min":10.0,
  "count":3
}
```
