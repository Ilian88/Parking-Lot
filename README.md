# Parking lot 

## Security
* Api uses basic authentication. At start there are only one user in db
  * User: ilian88
  * Pass: 12345
* Endpoint for creating users will be eventually introduced

## Database 
* Database should be installed and configuration put in the "src/main/resources/application.yml" accordingly

## Endpoints
* Vehicle enters in parking 
  * Endpoint: http://localhost:8080/vehicles/enter
  * It is a POST request
  * In body license plate and vehicle type must be specified
    * Vehicle type is either car or bus, case insensitive.
  * example body : {
    "licensePlate" : "BP0506CX",
    "vehicleType" : "Car"
    }
  * Validation
    * If vehicle with that plate number is already in the parking, error message will be returned
    * Vehicle registration must be at least 4 symbols long
    * vehicle type must be either car or bus as mentioned

* Vehicle leaves the parking
  * It is a POST request
  * Endpoint: http://localhost:8080/vehicles/exit
    * License plate of the vehicle that leave must be sent as query param
    * If there is no such vehicle in parking, error message will be returned
    * When vehicle leaves the parking, the time spent and amount will be calculated and stored in db
    * Example: http://localhost:8080/vehicles/exit?licensePlate=BP0506CX

* Get sales per time range
  * It is a GET request
  * Endpoint: http://localhost:8080/sales
    * start and end dates must be sent as query params in following pattern "yyyy-MM-dd"
    * if there are no results, empty array will be returned
    * Example: http://localhost:8080/sales?fromDate=2023-03-01&toDate=2023-04-04
    
* Get revenue each day in given month
  * It is a GET request
  * Endpoint: http://localhost:8080/sales/revenue
    * month and year must be sent as query params as digits
    * if there are no days with revenues in that month, empty array is returned
    * only days with revenue are returned
  * Example: http://localhost:8080/sales/revenue?month=5&year=2023

