
## Run Locally

Clone the project

```bash
  git clone https://github.com/dorosbence/simulation.git
```

Go the root directory of the project

Build the application by running

```bash
  mvn clean install
```
In terminal use the command below

```bash
java -jar ./target/ObjectMovingSimulation-0.0.1-SNAPSHOT.jar
```

For using the program, you have to give the init params first, for example:

```bash
  2,2,4,4
```

And then the action commands:

```bash
  1,4,1,3,2,3,2,4,1,0
```

Then you should see the result, like
```bash
  [1,0]
```
