# PLAN GENERATOR

sample payload:
{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}

sample response
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