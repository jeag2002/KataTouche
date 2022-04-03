### TEST KATA TOUCHE

Test exercice for TOUCHE

## Statement

Write a program that loads a set of employee records from a flat file,
and sends a greetings email to all employees whose birthday is today.

The flat file is a sequence of records, separated by newlines. These
are the first few lines:

last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com

The greetings email contains the following text:

Subject: Happy birthday!

Happy birthday, dear John!

The goal of this exercise is to come up with a solution that is:

- Testable: We should be able to test the internal application logic with no need to ever send a real email.
- Flexible: we anticipate that the data source in the future could change from a flat file to a relational database, or perhaps a web service. We also anticipate that the email service could soon be replaced with a service that sends greetings through Facebook or some other social network.
- Well-designed: separate clearly the business logic from the infrastructure.


## Technologies used:

- Microsoft Windows 10 Professional Edition x64
- Java 11.0.3
- JUnit 4.12 
- Mockito 2.0.2-beta

## Steps:

- Go to baselib of the project and execute the following command "mvn clean install"
- Go to {baselib}/target; and execute the following command "java -jar KataTouche-1.0.0-with-dependencies.jar -inputs=i1 -outputs=o1 -property=props.properties -date=YYYY/MM/DD"
  Being
  -inputs: active input listeners
  -ouputs: active output listeners
  -property: property file, empty value if you want default property file 
  -date: YYYY/MM/DD (D=Day, M=Month, Y=Year), empty value if you want today.
  
  e.g java -jar KataTouche-1.0.0-with-dependencies.jar -inputs=i1 -outputs=o1 -property= -date=2022/10/08

## Implementation tips:

The propertyFile manages all the aplication. Has this aspect 

---
i1.type=FILE							> Input listener type FILE
i1.file=input.txt						> File where is contained the data (included input.txt)
i1.header=true							> File's headers flag	
i1.columns=last_name, first_name, date_of_birth, email		> Headers
i1.output=o1							> Specific output/s related to input data. If empty the input data will send to
                                                                  all the active outputs.

o1.type=MAIL							> Output listener type MAIL
o1.host=smtp.mailtrap.io					> SMTP server host
o1.port=25							> SMTP server port							
o1.from=robot@foobar.com					> origin mail address
o1.subject=Happy birthday					> subject of the mail
o1.message=Happy birthday, dear {0}!				> body of the mail
---

the -inputs, -outputs input parameter flags active the different inputs/output configured in the propertyFile. 
Is mandatory to inform them (if not the application won't do anything)

