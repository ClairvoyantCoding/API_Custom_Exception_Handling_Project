# Java Spring Custom Exception Handling Article

In this article I cover:

- Creating custom application exceptions to be thrown in place of any exception you would not want a user to see
- Utilizing logging to persist the ugly error messages you wouldn't want users to see
- Using Controller Advice on a Spring Controller to catch the errors thrown by incoming requests
- Effective utilization of a global constant file

In this project I'll use Java 11, but this code should run in Java 8 with very little modification. It could also be ported over to Kotlin fairly easily as well.
