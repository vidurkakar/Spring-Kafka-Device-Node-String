# Spring Kafka Device Node

This repository contains the code for Kafka Device Node client.

Forked from previous work of [Anand J Bangad](https://github.com/anandjbangad/Spring-Kafka-Device-Node-String) at [CAS Lab](https://caslab.ece.vt.edu/).

Instructions to run -
1. Clone this repository.
2. Open this project in Intellij and IDE should automatically fetch the dependdencies from *maven*.
3. Run the project as a *SpringBoot Application* 
4. Run the GET commands in the following format - 

Example GET Commands can be found on my Post profile [here](https://documenter.getpostman.com/view/7078648/S1LwynQh)

A GET command has the following format -

```
http://yourBrokerIP:currentNodePort/serviceNumber?request=DestinationNode#SourceNode#MessagePayload#ResponseNode
```
Note: Escape character %23 is used for **#**

In the payload for OSDK command, we write in the following format - 
```
OSDK:theCommand:InputValuesIfAny
```

Example OSDK Payload - 
```
OSDK:move:1:0:0:0
```
Note: Escape character %3A is used for **:**

Example GET Command - 

```sh
http://localhost:8040/service1?request=cloudNodeReq%23deviceNode1%23HelloFromDeviceNode%23deviceNodeResp1
```

-------------------
 [CAS Lab, Virginia Tech](https://caslab.ece.vt.edu/)

