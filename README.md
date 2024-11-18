# Mortgage Calculator #

The **Mortgage Calculator API** is a Java RESTful API providing two endpoints:
### - POST /api/v1/mortgage-checks

Description: Accepts a MortgageRequest object and returns whether the mortgage is feasible and the monthly payment.

Validation: All fields in MortgageRequest are mandatory since they are required for the calculation.

I have implemented the **Strategy Pattern** to provide the code with more flexibility to changes. Currently, there is only a StandardMortgageCalculator, but different logics could be implemented.

Accepted inputs:
- **MaturityPeriod**: 30, 20 and 15 years mortgages. The input must be in months, so 360, 240 and 180 respectively. Other maturity periods will not be accepted.
- **MortgageType**: "STANDARD" is the only available type. Other types will not be accepted.


Request example:

```json
{
  "customerId": "8be702c9-0c5b-4dd0-9f9f-7baa727ed207",
  "mortgageType": "STANDARD",
  "income": {
    "amount": 300000,
    "currency": {
      "name": "Euro",
      "symbol": "€"
    }
  },
  "maturityPeriod": 360,
  "loanValue": {
    "amount": 140000,
    "currency": {
      "name": "Euro",
      "symbol": "€"
    }
  },
  "homeValue": {
    "amount": 175000,
    "currency": {
      "name": "Euro",
      "symbol": "€"
    }
  }
}
```

### - GET /api/v1/interest-rates

Description: Returns a list of interest rates.
Initialization: The interest rates are created in-memory during application startup.

# List of possible improvements:
- I would use hexagonal architecture instead of this simple structure
- Implement more Unit and Integration tests
- Find other approaches to implement the in-memory interest rates data, such as WireMock
- Changes when a DB is configured:
  - Properly create the required entities
  - Use mapstruct for mappings between entities and dtos
  - Implement the created_at and updated_at
- Add Security
- Improve logging
- Reduce hardcoded variables

### How to run:

- Download the code:
```
  git clone https://github.com/rocfabregas/mortgage-calculator.git
```
- Go inside the project folder:
```
  cd mortgage-calculator
```
- Install dependencies:
```
  mvn clean install
```
- Run the app:
```
  mvn spring-boot:run
```
- Test the endpoints with these Curls:
  - POST
```
  curl --location 'http://localhost:8080/api/v1/mortgage-checks' \
--header 'Content-Type: application/json' \
--data '{
    "customerId": "8be702c9-0c5b-4dd0-9f9f-7baa727ed207",
    "mortgageType": "STANDARD",
    "income": {
        "amount": 300000,
        "currency": {
            "name": "Euro",
            "symbol": "€"
        }
    },
    "maturityPeriod": 360,
    "loanValue": {
        "amount": 140000,
        "currency": {
            "name": "Euro",
            "symbol": "€"
        }
    },
    "homeValue": {
        "amount": 175000,
        "currency": {
            "name": "Euro",
            "symbol": "€"
        }
    }
}'
```
  - GET

```
curl --location 'http://localhost:8080/api/v1/interest-rates'  
```

# Thanks for reading. Any feedback will be appreciated.