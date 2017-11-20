# Chronos

To run the project in Eclipse:
-Right click the project and go to Properties
-In "Java Build Path", in the libraries tab, remove the JAR file that starts with "mysql-connector-java".
-Click "Add external JARs" and go into the project directory and go to WebContent/WEB-INF/lib and add the JAR file in there
-Click "Apply"
-Go to "Deployment Assembly", click "Add", click "Java Build Path Entries", click "Next", click the "mysql-connector-java" file, click "Finish".
-Click "Apply and Close"
-Open index.jsp and run the project.