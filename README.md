# banking-app

This CLI application simulates a simple banking management terminal.
Note that this app is designed to be used for managing the services of an imaginary bank (let's call it PAO Bank) and not to be used by a customer of a bank, therefore not having a login functionality.

## Project Timeline
### 1st Phase

#### Requirements
System with
- at least 10 actions/ queries that can be made;
- at least 8 types of objects.

To implement:
- classes having private/ protected data members & access methods.
- at least 2 distinct collections for managing the objects defined previously (e.g. List, Set, Map, etc.) - and at least 1 sorted (implement Comparable).
- use inheritance.
- at least 1 service class exposing the actions.
- 1 Main class - making calls to the services.

## Structure
- package accounts
    - abstract class Account
        - class CheckingAccount
        - class SavingsAccount
    - class AccountFactory
- package card
    - class Card
- package atm
    - class Atm
    - abstract class AtmTransaction
        - class Deposit
        - class Withdrawal
- package transfer
    - class Transfer
- package Utils
    - class Address
    - class RandomGenerator
- package services
    - class ClientService
    - class AccountService
    - class TransactionService
    - class AtmService
    - class BankingInteractor
- class Main (uses class BankingService)

Notes:
The withdrawal function should only work if the bank account has enough money to withdraw a requested amount.

## Actions
- TODO

TODOs
- Builder pattern?
- exception handling
- add javadocs
- use interfaces for services
- Builder class?
- Add currency class
- Logging class?
  final:
- JUnit testing

Later on:
- add GUI?

Notes
- time has been preferred to Date [useful article](https://stackabuse.com/how-to-get-current-date-and-time-in-java/)
- I chose to not implement a class with constants or declare my constants as static final, but instead, I used enums.
  [useful article](https://tedvinke.wordpress.com/2016/04/14/2-rookie-java-constants-and-enums-pitfalls/)
- Used BigDecimal s for balance instead of double - because floating point values are not precise.
