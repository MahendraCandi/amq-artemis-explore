# AMQ-Artemis Explore App

1. Download the [AMQ-Artemis](https://www.apache.org/dyn/closer.cgi?filename=activemq/activemq-artemis/2.19.0/apache-artemis-2.19.0-bin.zip&action=download)
   then extract it to a directory
2. Go to /bin directory then Open CMD/terminal
3. Create the messaging broker using command:

    `artemis create <your-broker-name> --name mybroker --user <username> --password <password>`
   
4. Go to `<your-broker-name>`
5. Create address:
    `artemis address create --user <username> --password <password> --name <address-name>`
6. The `<address-name>` is represented as our queue name
7. You can go to http://localhost:8161 to check if AMQ is already. Furthermore, you can open the Management Console to check your address, queue, and browse the message
