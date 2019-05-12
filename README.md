## BASIC EMAIL SERVER
The code has a REST API with a URL exposed  . You can use that to call the api. The sending of the api will take place on a spearate thread as annotated by @async method. The Execution will also be tried multiple times as it is used with spring-retry framework.
The Integration test test the User input validations and a basic flow .
Corresponding unit test are also provided  