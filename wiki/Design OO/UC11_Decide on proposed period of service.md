UC11 - Decide on proposed period of service
==========================================

## Rational

| Main Stream                                                                                          | Question: What Class ...                                     | answer                                       | Justification                                                                                                     |   
|:-----------------------------------------------------------------------------------------------------------|:------------------------------------------------------------|:-----------------------------------------------|:---------------------------------------------------------------------------------------------------------------------|
|1.The client initiates the decision about the proposed period for performing services. |... interacts with the user? |PeriodDecisionUI|Pure Fabrication|
||...coordinates the UC?|PeriodDecisionController|Controller|
||...who knows the class AffectedOrders?|AffectedOrdersStorage| HC + LC |
||...who knows the class Client?| ClientsStorage| HC + LC |
||...who knows the class AffectedOrdersStorage?| Company | HC + LC |
||...who knows the class ClientsStorage?| Company | HC + LC |
|2. The system shows the list of requests waiting for confirmation.|... Do you know the orders waiting for confirmation? | AffectedOrdersStorage|IE: AffectedOrdersStorage possui Pedidos afetados|
|3. The client selects an order.| |||
|4. The system displays the assigned time.|... knows the assigned time?| AffectedOrders| HC + LC|
|5.The client confirms the displayed time.| | ||
|6. The system generates a service execution order for each service provider involved.|...who knows the class ExecutionOrder?| ExecutionOrdersStorage | HC + LC |
||...who knows the class ExecutionOrdersStorage?|Company|HC + LC|
||...saves ExecutionOrder?| ExecutionOrdersStorage| IE: ExecutionOrdersStorage knows all ExecutionOrder|

                                        
## Systematization ##

 From the rational it results that the conceptual classes promoted to classes of software are:

 * Client
 * Company
 * AffectedOrder
 * ExecutionOrder

Other software classes (i.e. Pure Fabrication) identified:

 * PeriodDecisionUI
 * PeriodDecisionController
 * ClientStorage
 * AffectedOrdersStorage
 * ExecutionOrdersStorage

##	Sequence Diagram

![SD_UC11_LAPR2.png](https://bitbucket.org/repo/A6gE67j/images/1823352648-SD_UC11_LAPR2.png)

##	Class Diagram

![CD_UC11_IT3_LAPR2.png](https://bitbucket.org/repo/A6gE67j/images/1619402487-CD_UC11_IT3_LAPR2.png)