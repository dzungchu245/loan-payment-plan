## PLAN GENERATOR

## 1. Build & start application
Build
```sh
mvn clean package
```
Run Unit Test
```sh
mvn test
```
Start application
```sh
mvn spring-boot:run
```
## 2. Test with swagger-ui
- Open web browser, navigate to 
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html )
- Run API : 
```sh
POST  http://localhost:8080/generate-plan 
```
* Request body:

|Params | Data Type | Description |
|-------|-------|-------| 
|loanAmount | Double | Loan Amount |
|nominalRate | Double | Nominal Rate (%) |
|duration | Integer | Duration (months) |
|startDate | LocalDateTime | Start Date |

Enter Json request then Execute it.

#### Note: By the provided API information, we can also test it by external tool(Postman) or integrate it with other system    

Sample request:
```sh
{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```

Sample response 
```sh
[
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2018-01-01T00:00:01",
    "initialOutstandingPrincipal": 5000,
    "interest": 20.83,
    "principal": 198.53,
    "remainingOutstandingPrincipal": 4801.47
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2018-02-01T00:00:01",
    "initialOutstandingPrincipal": 4801.47,
    "interest": 20.01,
    "principal": 199.35,
    "remainingOutstandingPrincipal": 4602.12
  },
  .
  .
  .
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-11-01T00:00:01",
    "initialOutstandingPrincipal": 435.91,
    "interest": 1.82,
    "principal": 217.54,
    "remainingOutstandingPrincipal": 218.37
  },
  {
    "borrowerPaymentAmount": 219.28,
    "date": "2019-12-01T00:00:01",
    "initialOutstandingPrincipal": 218.37,
    "interest": 0.91,
    "principal": 218.37,
    "remainingOutstandingPrincipal": 0
  }
]
```
## 3. Exception Handler
A valid request must be as following:
- loanAmount > 0
- nominalRate > 0
- duration > 0
- startDate is a valid date

If request is invalid, for example

```sh
{
"loanAmount": 0,
"nominalRate": 0,
"duration": 0,
"startDate": ""
}
``` 
then API will return response
```sh
{
  "errors": [
    "BAD_REQUEST"
  ]
}
``` 


