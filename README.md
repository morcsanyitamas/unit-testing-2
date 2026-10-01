# Unit testing II. - Test doubles

## Understanding the problem
* Review the starter code within the com.codecool.bonusintheair.base package.
* Notice that in the tests for `CompanyProfitCalculator`, we are not only testing the profit calculation logic but also the functionality of `BrokerSalaryCalculator`.
    * This means that if the salary calculation logic changes or is incorrect, the profit calculation tests might also fail, even if there is nothing wrong with the profit calculation itself.
    * This contradicts the fundamental principle of unit testing: a test should only test one thing and should not depend on the implementation of other classes.
    * The solution would be to use test doubles (like mocks, stubs, or fakes) to replace the real `BrokerSalaryCalculator` with a controllable implementation during testing.

## Task: Refactoring of the CompanyProfitCalculator
Now that we understand the problem, let's refactor the code.
1. Extract Interface
2. Make `BrokerSalaryCalculator` implement the interface
3. Apply Dependency Injection
4. Create Test Doubles in test

## Task: Implementing a Notification System

### Goal
Extend the existing CompanyProfitCalculator implementation with a notification system that sends alerts in critical situations.

### Requirements
#### NotificationService Interface
Create a NotificationService interface with the following methods:
* void sendAlert(String message) - for sending critical alerts
* void sendInfo(String message) - for sending informational messages

#### ConsoleNotificationService Implementation
Implement a ConsoleNotificationService class that:
* Writes messages to the terminal
* With an [ALERT] prefix for alert messages
* With an [INFO] prefix for info messages

#### Extending CompanyProfitCalculator
Modify the CompanyProfitCalculator class to:
* Accept a NotificationService via dependency injection
* Send an alert if the remaining profit is less than -1000
* Send info at the start of every calculation about the number of brokers
* Throw an exception (IllegalStateException) if the total profit is negative AND the remaining profit is less than -5000 (critical loss)

#### Testing Requirements
Write unit tests that:
* Verify notification calls using a mock
* Test for the exception being thrown in case of critical loss
* Verify that no notifications are sent in a normal case (Note: This requirement might need adjustment based on the "send info" requirement above).
* Use Mocikto annotations for creating mocks and injecting dependencies.

## Extra: Refactoring of the BrokerSalaryCalculator
A common question in unit testing is when to abstract a dependency behind an interface. The `BrokerSalaryCalculator` provides a perfect example of when it is not necessary.

### Identifying the Type of Dependency
The `CompanyProfitCalculator` depended on the behavior of the BrokerSalaryCalculator (a class with logic). This created a problem: if the salary logic was wrong, the profit tests would fail.

However, the `BrokerSalaryCalculator` depends on a `List<BonusRule>`. A `BonusRule` is a record, which is a simple, transparent data carrier. It has no complex internal logic or behavior that can change.

This is the key distinction:
* *Dependency on Behavior*: A class needs another class to perform a calculation or execute an action (e.g., fetch from a database).
* *Dependency on Data*: A class needs information to perform its own calculations (e.g., a list of rules, a configuration object, a String).

### Controllability is the Goal
In the case of `BrokerSalaryCalculator`, we already have full control. The list of rules is injected via the constructor (Dependency Injection). In a unit test, we can easily create a `List<BonusRule>` with our exact test data and pass it in.

### Conclusion
The `BrokerSalaryCalculator` is already a perfect example of a unit-testable class because its dependencies are controllable, predictable data, not complex, hidden logic.