# spring-data-jpa-rest-workshop
========================

---
**NOTE**
This repository is DEPRECATED!
---


Example using Spring Data JPA with REST exporter.

You can use Google Chrome´s extension "Dev HTTP Client" or curl to experiment the REST usage.

For example you can this JSON data to add an address via a POST request:

http://localhost:8080/addresses

{
  "country" : "Deutschland",
  "city" : "Stuttgart",
  "street" : "Elchstrasse 4"
}

Headers: Content-Type:application/json
