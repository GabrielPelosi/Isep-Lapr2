# Realization UC1  Register as Customer

## Rational

| Main Flow                                                                                        | Question: What Class...                                      | Answer                                      | Justification                                                                                                         |
|:-------------------------------------------------------------------------------------------------------|:------------------------------------------------------------|:-----------------------------------------------|:---------------------------------------------------------------------------------------------------------------------|
|1. The unregistered user starts registering as a customer.|... interacts with the user?|RegistarClienteUI|PureFabrication|
||...coordinates the UC?|RegisterClientController|Controller|
||...criate/instance Client?|RegistoClients| HC + LC (about Company) + Creator (Rule 1)|
||...who knows the RegistoCliente?|Company| HC + LC |
|2. The system requests data (i.e. full name of the person, NIF, tlelphone contact, email and password).| | | |
|3. The unregistered user enter the requested data.| ... save the entered data?|Client|Information Expert (IE) - instance created in step 1|
|4. The system requests one postal address.||||
|5. The unregistered user enters postal address.|...create/instance Postal Address?|Client|Creator (Rule 4)|
|| ... save the entered data?| Postal Address |Information Expert (IE) - instance created in this step|
|||Postal Code| IE: One Postal Addres have one Postal Codel|
|6. The system validates and save the entered postal address.|... save the postal address created?| Client|Information Expert (IE) - In MD the customer mentiones one or more Postal Addresses|
|7. The steps 4 to 6 are repetated until all the required postal addresses are entered (minimum 1).||||
|8. The system validates and show data, asking to confirm them.|...validate customer data (local validation )?|Client|E: Customer has their own data|
||...validate customer Dcata (global validation)?|RegistoClientes|IE: The registoClientes contains/adds Clients|
|9. The unregistered user confirms. ||||
|10. The system registers the customer and registered user data and informs the unregistered user of the success of the operation. the customer register contains / adds Services|
|| ... save the user data for this client?  | AutorizacaoFacade | IE. 
User management is the responsibility of the respective external component whose point of interaction is through the class "AuthorizationFacade" |   
|| ... notify the user?  | RegisterClientUI | |                                               

## Systematization##

From the rational it results that the conceptual classes promoted to classes of software are:

 * Company
 * Postal Address
 * **Postal Code**
 * Client
 * **RegisterClients**

Other software classes (i.e. Pure Fabrication) identify:  

 * RegisterrClientUI  
 * RegisterClientController

 ** Note: ** The responsibility of creating instances of Postal Address to the Customer has been assigned.
However, a customer is only valid when he has at least one Postal Address.
That is, to exist a Customer it is necessary to have a Postal Address.
Thus, it is not feasible to ask a Client instance to create a Postal Address.
To resolve this issue the creation of instances of Postal Address is done through a static method in the Client class. Therefore, it is not necessary to have a client instance previously. Other approaches / alternatives are possible.

##	Sequence Diagram

![SD_UC1.png](https://bitbucket.org/repo/A6gE67j/images/3207275786-SD_UC1.png)


##	Class Diagram
![CD_UC1.png](https://bitbucket.org/repo/A6gE67j/images/667614553-CD_UC1.png)