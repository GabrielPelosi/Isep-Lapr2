# UC1 - Register as Customer

## Short Format

The unregistered user starts the registration as a client. The system requests the necessary data (i.e. the person's full name, NIF, telephone contact, email, password, and at least one postal address). The unregistered user enters the requested data. The system validates and displays the data, asking you to confirm them. The unregistered user confirms. The system records the customer and registered user data and informs the unregistered user of the success of the operation.

## SSD
![SSD_UC1.png](https://github.com/GabrielPelosi/Isep-Lapr2/blob/master/wiki/Use%20Cases/SSD_UC1.png)


## Full Format

### Main Actor

Unregistered User

###  Stakeholders and their interests
* **Unregistered User:** wants to register so that you can enjoy the services provided by the company.
* **Company:**  wants the person concerned to become a customer in order to request services.


### Pre-conditions
n/a

### Post-conditions

The registration information is stored in the system.

## Main success scenario (or basic flow)

1. The unregistered user starts the registration as a client.
2. O The system requires the necessary data (i.e. the person`s full name, NIF, telefone contact, email and password). 
3. The unregistered user enters the requested data. 
4. The system requests postal address.
5. The unregistered user enters the postal code.
6. The system validates and save the entered postal code.
7. The steps 4 to 6 repeated until all the required postal addresses are entered (minimum 1).
8. The system validates and show the data, asking to confirm them.
9. The unregistered user confirms. 
10. The system **registers the customer and registered user data** and inform the unregistered user of the success of the operation.
### Extensions (or alternative flows)

*a. The unregistered user requests the cancelaation of registration.
> The use case ends.

**4a. Duplicates NIF and/or E-mail address.**
>	1. The system informs  the user about duplicate data.
>	2. The system allows to enter new data (step 3)
>
	>	2a. The unregistered user don`t change data . The use case ends.

6b. Incomplete Postal address data.
>	1. The system informs tells which data is missing.
>	2. The system allows to enter the missing data (step 5)
>
	>	2a. The unregistered user don`t change data. The use case ends.
	
8a. Minimum missing data required.
>	1. The system informs which data is missing.
>	2. The system allows to enter the missing data (step 3)
>
	>	2a. The unregistered user don`t change data. The use case ends.

8b. The sytem detects that the data (or some subset of the data) entered must be unique and already exist in the system
>	1. The system alerts unregistered user.
>	2. The system allows to change (step 3)
>
	>	2a. The unregistered user don`t change data. The use case ends.

8c. The system detects that the data entered (or some subset of the data) is invalid.
> 1. The system alerts unregistered user to the fact. 
> 2. The system allows the change (step 3).
> 
	> 2a. The unregistere user don`t change data.The use case ends. 

### Special requirements
\-

### List of variations of technologies and data
\-

### Frequency of occurence
\-

### Open questions
\-
