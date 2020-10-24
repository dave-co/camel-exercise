###  About

Simple spring boot/camel project to demonstrate a REST endpoint with schema validation, transformation and error handling.

### Build JAR

`mvn clean package`

### Create Docker image

`docker build -t camel-exercise .`

### Run Docker image

`docker run -p 8080:8080 -t camel-exercise`

### Endpoint URL

http://localhost:8080/camel/vehicle/add

### Valid request

```
<?xml version="1.0" encoding="UTF-8" ?>
<addVehicle>
  <assetId>1</assetId>
  <make>ford</make>
  <model>mondeo</model>
</addVehicle>
```

### Invalid request

```
<?xml version="1.0" encoding="UTF-8" ?>
<addVehicle>
  <assetId>text</assetId>
  <make>ford</make>
  <model>mondeo</model>
</addVehicle>
```
