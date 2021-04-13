# java_federation_graphql
This is a sample project on how to implement GraphQL and Apollo Federation in Spring Boot -Java
3d party apollo federation library for implementing services -> https://github.com/rkudryashov/graphql-java-federation 
Apollo server with library @apollo/gateway

```java
repositories {
  jcenter()
}
```

```java
dependencies {
    implementation("io.gqljf:graphql-java-federation:$graphqlJavaFederationVersion")
}
```

To start Apollo Server -> 
  node gateway.js
