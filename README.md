# AMQ-Artemis Explore App

**Start the broker message**
1. Download the [AMQ-Artemis](https://www.apache.org/dyn/closer.cgi?filename=activemq/activemq-artemis/2.19.0/apache-artemis-2.19.0-bin.zip&action=download)
   then extract it to a directory
2. Go to /bin directory then Open CMD/terminal
3. Create the messaging broker using command:

    `artemis create <your-broker-name> --user <username> --password <password>`


**Create an address/a queue**
1. Go to `<your-broker-name>`
2. Create the address:
    `artemis address create --user <username> --password <password> --name <address-name>`
3. The `<address-name>` is represented as our queue name
4. You can go to http://localhost:8161 to check if AMQ is already. Furthermore, you can open the Management Console to check your address, queue, and browse the message
5. Or just execute the API's provide by this service then the queue should be create automaticly

**How to run this service**
1. run as producer:

    `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081 --queue.listening.name=xxx --topic.listening.name=xxx --spring.devtools.livereload.port=35729"`

2. run as consumer:

    `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082 --spring.devtools.livereload.port=35729"`

**Send message from cmd/terminal**
1. From artemis root directory go to /bin
2. Run this command `artemis producer --destination queue://<queue-name> --message-count 1 --user <username> --password <password> --message "hello" --verbose`

**The Provided API**
1. Send to a queue:
    `http://localhost:8081/send?message=hello&count=10`
2. Send to a queue with json request body
    `http://localhost:8081/send-body` 
   and please specify the payload as json, example
   `
   {
        "movie":"AOT",
        "arc":"The Rumbling"
   }
   `
3. Send to a topic
   `http://localhost:8081/send-topic?message=hello&count=10`
