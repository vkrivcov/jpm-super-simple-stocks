# jpm-super-simple-stocks

Super Simple Stocks Emulator usage instructions:

Prerequisites:
a) Maven
b) Java 1.7 or 1.8

1. Clone the project to your local folder:
git clone https://github.com/vkrivcov/jpm-super-simple-stocks.git

2. Enter cloned project directory
cd jpm-super-simple-stocks

3. Package the project using mvn (unit tests will be automatically invoked, but you can run them separately using "mvn test" command)
mvn clean package

4. Run the Super Simple Stocks Emulator application
java -jar target\super-simple-stock-1.0-SNAPSHOT.jar (windows)
java -jar target/super-simple-stock-1.0-SNAPSHOT.jar (linux)

Usage of the emulator should be simple and self-explanatory i.e. simple select available example stock (initial user menu)
and run stock manipulations emulator using specified options.
