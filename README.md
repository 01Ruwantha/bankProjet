<div align = "center"><img src="https://github.com/01Ruwantha/bankProjet/blob/a69690d755e4ffe44320b7f239e2d7ee63a47feb/Bank.png" alt="Bank.png"  /></div>

<h1>Banking Management System </h1> 

## Project Overview
Now a days every one very busy in their work. So, they feel that the job must be easier so the system is used to reduce their work which is done in the Banking system. Instead of keeping lots of paper into a record or file and it may be missed somewhere so, this system helps to keep the record of the customer it also keeps the details of the customer. It is also easy to access

<div align = "center"><img src="https://github.com/01Ruwantha/bankProjet/blob/6e19d2f54bcaae80abdc7629f077094f868461f2/project.gif" height="300"  alt="Project GIF"  /></div>



## Team Members
- **P.V. Ruwantha Madhushan** - Project Leader
- **P.V. Nimantha Madhushan** 


## Features and Benefits
This banking system provides the following benefits:
- Facilitates transactions.
- Offers high security.
- Provides account creation and deletion facilities.
- User-friendly interface to simplify operations.
- Efficient record-keeping without paper documents.

## Project Structure

### Classes
1. Main
2. Admin
3. User
4. Account
5. Security
6. FileHandling
7. Audio
8. LaunchPages
9. AdminPages
10. UserPages

### Interfaces
1. InterfaceAccount
2. InterfaceSecurity
3. InterfaceAdminOnly
4. InterfaceUserAdmin

## Class Responsibilities

### P.V. Ruwantha Madhushan
- Main class
- AdminPages class
- FileHandling class
- Account class
- Admin class
- Interfaces: InterfaceUserAdmin, InterfaceAdminOnly

### P.V. Nimantha Madhushan
- LaunchPages class
- UserPages class
- User class
- Audio class
- Security class
- Interfaces: InterfaceAccount, InterfaceSecurity
  
## Key Functionalities

### User
- signUp()
- signIn()
- changePassword()
- forgotPassword()
- deleteAccount()
- history()

### Admin
- signUp()
- signIn()
- allAccountReport()
- updateAccount()
- changePassword()
- forgotPassword()
- deleteAccount()
- history()

### FileHandling
- fileWriteDetail(accountNumber, accountName, userPassword, accountBalance)
- getInfoInAllAccReportToArray()
- changePassword(accountNumber)
- updateAccount(updateUserAccountNumber)

### Security
- checkAccount(accountNumber)
- checkPassword(accountNumber, userPassword)
- checkPhoneNumber()

## Object-Oriented Programming Concepts Applied
- *Inheritance*: Demonstrated through class hierarchies.
- *Polymorphism*: Method overloading and overriding.
- *Encapsulation*: Private attributes with controlled access.

## Challenges Faced and Solutions
*Challenges:*
- Developing GUI interfaces.

*Solutions:*
- Utilized online tutorials:
  - [Bro Code](https://youtu.be/Kmgo00avvEw)
  - [Alex Lee](https://youtu.be/5o3fMLPY7qY)

    
