# Supplementary Specification

## Functionalities

It specifies the features that do not relate to the use cases, namely: Audit, Report, Local / Languages ​​and Security.

- ** Audit and Report: **
    * A systematic process is carried out in which all the activities developed in the company, in this case, service company are analysed carefully. The goal is to verify that the tasks are being performed according to the predetermined procedures by the administrative.
    * The identified nonconformities are reported quickly to the administrative staff. In this way, it is possible to develop action plans to minimize the identified failures.

-  **Safety:**
    * User interactions (i.e. Client, Service Provider, Administrative, Human Resource Employee) must be preceded by an authentication process.
    * Exceptionally some well defined features are accessible without authentication ("The use of the application by other people is restricted...").
    * Interactions of users (i.e. Client, Service Provider, Administrative, Human Resources Officer) must be preceded by an authentication process.

-  **Location/Languages:**
    * The system must foresee aspects related to internationalisation of the application, namely in terms of support for multiple languages and time zones.


## Usability

Evaluates the user interface. It has several subcategories, among them: error prevention; aesthetics and design; help and documentation; consistency and standards.

- **Error Prevention:**
    * It is essential to always be one step ahead of users, and to predict any mistakes they may make.  
    * In fact, we are all users, so we must think as one, to prevent the maximum number of possible errors that can occur.
    * It is necessary to look for the weaknesses of the software, where, for example, users can be confused and then correct this error.

- **Aesthetics and Design:**
    * A well-organised interface should be presented to the user so that the user feels and is satisfied with the use of the appropriate software in the context.
    * It is also intended that each company can customise according to their preferences the colour scheme adopted by the application.

- **Help and Documentation:**
    * The documentation is a written text that accompanies the software and generally explains how to use it.
    * Each software must be properly documented, so that if the user has doubts about the operation of the same, it may have information available to use the system correctly.
    * Software documentation can further assist programmers on system content by facilitating use and development for future developments.

- **Consistency and Standards:**
    * Consistency is one of the main characteristics for the usability of an interface.
    * This reduces the conflict caused by unexpected and logically incomprehensible software behaviour.
    * In addition, it allows a person to generalize knowledge of one aspect of a software to others.
    * To be consistent, menus, input commands, information views, and all the functions of an interface must have the same visual presentation and behaviour.

## Reliability
Refers to software integrity, compliance, and interoperability. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of forecasting, accuracy, mean time between failures.

- **Fault Frequency and Severity:**
    * The frequency and severity of software failure should be the lowest, for user satisfaction and software credibility.

- **Possibility of Recovery and Forecast:**
    * Whenever an anomaly occurs in the software it must be recovered in the shortest time possible for user satisfaction.
    * Possible software anomalies should be anticipated and corrected for correct functioning of the software.

## Performance
It evaluates the software performance requirements, namely: response time, memory consumption, CPU utilisation, load capacity and application availability. Faced with these software performance requirements, the user always intends to optimize them, that is, response time, memory consumption and CPU utilisation should be as low as possible. The load capacity and availability of the application must be high to guarantee the user, for example, the fast processing of data. To reduce the computational complexity (memory and run-time) and easy the implementation and testing, only a subset of zip codes corresponding to Porto region will be used in this work.

## Sustainability
The supportability requirements group several characteristics, such as testability, adaptability, manutibility, compatibility, configurability, instability, scalability, among others.

* The software must be pre-tested in order for it to function properly, but it must be tested in the event of a fault finding, correcting it.
* Software must be functional on any machine that contains any operating system.
* Unstable faults, anomalies or other aspects of the software must be corrected with the maximum urgency.

- ** Configurability: **
* Company information (e.g. designation, NIF and algorithms for job scheduling) must be specified by configuration.
* The system must use an external service, defined by configuration, to obtain postal codes to be covered in the geographical area.
* The process of installing and configuring the system in a company must be very simple, intuitive, fast and above all suitable for people with little technical knowledge.

- ** Testability: **
* The implementation process must follow a TDD (Test Driven Development) approach. Unit tests should be developed to validate all domain classes. Code changes must follow the same criteria, i.e. when changing existing components, unit tests must be developed or updated. The final evaluation of the project will include an analysis of the quality of testing and the use of a test-driven development approach. Code coverage and mutation coverage is based on unit testing and is performed using quality assurance tools.
* The system has a minimum coverage and mutation rate (e.g. unitary, functional and integration) of 75%.

### Design Restrictions

Specifies or restricts the system design process. Examples may include: programming languages, software process, use of development tools, class library, etc.

- Adopt good practices in identifying requirements and analyzing and designing OO software.
- The software development process should be iterative and incremental while adopting good design practices and coding standards.
- Reuse the existing user management component in the company.
- Support different external systems to obtain postal codes.
- All the algorithms implemented in this work should be highly efficient.

### Implementation Restrictions

Specifies or restricts the code or build of a system such as: mandatory patterns, deployment languages, database integrity policies, resource limits, operating system.

- Both applications should be implemented in Java and the user interface should be implemented using JavaFX, a Java library used to build Rich Internet Applications.
- Adopt recognized coding standards.

### Interface Restrictions

Specifies or restricts the functionality inherent in the system's interaction with other external systems.

- Support different external systems to obtain postal codes.


### Physical Restrictions

Specifies a limitation or physical requirement of the hardware used, for example: material, shape, size or weight.