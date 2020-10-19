UC11 - Decide on proposed Period of Service Realization
==========================================


## Short Format

The client initiates the decision about the proposed period of service realization. The system shows the pending service requests from the decision and asks the client to choose one. The client chooses an order. The system presents the timespans stipulated for the service and requests a decision (either accept or reject). The client decides. If the client accepts, the system registers the decision and generates a service execution order for each service provider involved. Otherwise, the system records the decision. The system completes the operation by recording the client's decision.

## SSD
![SSD_UC11_LAPR2.png](https://bitbucket.org/repo/A6gE67j/images/1953369725-SSD_UC11_LAPR2.png)

## Complete Format

### Main Actor

Client

### Stakeholders and their interests
* **Client:** wants all the services of the order to be according to their preference.
* **Company:** understands that client preference is possible to meet by the designated Service Provider and time.



### Pre-conditions
There is a Service Provider(s) attached to a request defined in the system.

### Post-conditions
The decision information is stored in the system.

## Base Flow

1. The client initiates decides about the proposed period for a service to be performed.
2. The system displays the pending service requests and prompts to a choice.
3. The client chooses one of the forementioned orders.
4. The system presents the timespan stipulated for the service and requests a decision (either accepts or rejects).
5. The client decides.
6. If the client accepts, the system registers the decision and generates a service execution order for each service provider involved with the order(s). Otherwise, the system records the decision. 
7. The system completes the operation by recording the client's decision.

### Extensions (or alternate flows)

*a. The client cancels the decision made beforehand.

> The use case ends.



### Special requirements
\-

### List of Variations of Technologies and Data
\-

### Frequency of Occurrence
\-

### Open questions