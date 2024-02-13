### Hexlet tests and linter status:
[![Actions Status](https://github.com/ConstableFraser/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ConstableFraser/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/1a4fe2773eae4dbac527/maintainability)](https://codeclimate.com/github/ConstableFraser/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/1a4fe2773eae4dbac527/test_coverage)](https://codeclimate.com/github/ConstableFraser/java-project-78/test_coverage)

# DESCRIPTION PROJECT
## name "validator"
this is an educational project to create a software library

## key features
1. The library is extensible. The consumer can create their own types of checks
2. Three ready-made ckecks "in box": string, integer and map

## how to use
```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// schema to check a string
StringSchema schema = v.string().required();

schema.isValid("this string is correct"); // true
schema.isValid(""); // false, because a parameter is required
schema = v.string().minLength(20);
schema.isValid("16 letter string"); // false, because less than 20 letter 


// schema to check a number
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

schema = v.number().required().range(5, 10);
schema.isValid(2); // false
schema.isValid(9); // true
```

## diagram class
![Diagram](media/DiagramClass.png)

## tech stack
Java core: collections, stream API, functional interface;\
Unit tests: JUnit (jupiter) and assertj, Jacoco;\
Linter: checkstyle.
