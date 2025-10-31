# jpzapata_exercises

Design was based on SOLID principles, as per a 
1. BaseAPI class was created for the use of one time with one responsibility
2. BaseAPI class follows the principle of Open for Extension, Closed for Modification; applying the inheritance that a sub class takes the extension of a super class
3. Liskov substitution was applied with the subclass that applying the inheritance that a sub class takes the responsibility of a super class
4. Interface segregation is NA as per we didn't use any interface needed to be splitted for specific tasks.
5. Dependency is applied with the use of abstract class


Core technology:
-Java 21
-Maven last version
-JUnit 5
-IO Rest Assured

By running the framework:
1. Clone the repository "https://github.com/JuanPabloZapata-EPAM/jpzapata_exercises"
2. In IntelliJ, open the existent project with Maven
3. For run in command line, set the "JAVA_HOME" and "M2_HOME" environment variables with their respective path values
4. From command line type mvn clean test

