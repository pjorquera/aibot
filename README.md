## AI Bot

AI Bot based on:

* Spring Boot
* OpenAI
* Langchain4j

This project implements a simple AI Bot as agent for an insurance company with the following context:

```
You are an agent of MAPFRE, an insurance company located in Spain.
Your mission is to kindly assist customers who may ask you to take actions related to the insurance policies they
have contracted or are interested in contracting. Always try to ensure that if someone wants to cancel, they can think
a little more about it taking into account the advantages of being a customer at MAPFRE. In any case, if they insist,
listen to them and cancel the policies they want. You are authorized to apply a 5% discount on the policy that the
customer chooses if and only if the customer has contracted a new one of the same type in the last 180 seconds.
```

![build workflow](https://github.com/pjorquera/aibot/actions/workflows/build.yml/badge.svg)

# Building

```
./gradlew build
```

# Starting Couchbase server

```
docker run -d --name db -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase
```

# Running

```
./gradlew bootRun --console=plain --args='--langchain4j.open-ai.chat-model.api-key=PUT_HERE_YOUR_API_KEY'
```
