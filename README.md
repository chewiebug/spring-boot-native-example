# Measurements

environment:
- Windows 10 21H2
- Intel i5-6600 (4 Core)
- 16GB RAM
- Docker Desktop 4.4.4
- Docker engine v20.10.12

empty spring boot initializr project (modules: spring-boot-starter, spring-native)
                       standard java; native (tests skipped)
build time             < 3s         ; (spring-boot:build-image) 2 Min 30s (image size: ca. 50MB)
startup time           1.786s       ; 0.013s

spring boot initializr project (modules: spring-boot-starter-web, spring-native)
                       standard java; native
build time             736m         ; (spring-boot:build-image) 4 Min 02s (image size: ca. 90MB)
startup time           < 4s         ; 0.055s
response times (1st)   70 - 80ms    ; 1ms
response times (later) 1 - 20ms     ; 1ms

## observations
- first build with spring-boot:build-image usually crashed Docker Desktop for Windwos (out of memory)
  - second build usually was ok -> because of caching of intermediate image layers?

## minimal vm to run tests against (docker file: src/main/docker/Dockerfile.jvm):
- docker run --rm -it -p 8081:8081 --memory=70M --cpus=0.093 --env JAVA_MAX_MEM_RATIO=35 --env JAVA_INITIAL_MEM_RATIO=20 --env GC_MAX_METASPACE_SIZE=35 --env GC_CONTAINER_OPTIONS=-XX:+ExitOnOutOfMemoryError --env JAVA_OPTS_APPEND=-Xlog:gc*=info,safepoint,os+cpu:file=/home/jboss/gc.log:tags,time,uptime,level --mount type=bind,src=$pwd/target,dst=/home/jboss/ chewiebug/spring-boot-jvm
- needs 60s test run, because of lengthy initialisation of the first requests

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/#build-image)
* [Spring Native Reference Guide](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)

### Additional Links
These additional references should also help you:

* [Configure the Spring AOT Plugin](https://docs.spring.io/spring-native/docs/0.11.3/reference/htmlsingle/#spring-aot-maven)

## Spring Native

This project has been configured to let you generate either a lightweight container or a native executable.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started with Spring Native.
Docker should be installed and configured on your machine prior to creating the image, see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.11.3/reference/htmlsingle/#getting-started-buildpacks).

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image
```

Then, you can run the app like any other container:

```
$ docker run --rm spring-boot-native-example:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM native-image compiler should be installed and configured on your machine, see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.11.3/reference/htmlsingle/#getting-started-native-build-tools).

To create the executable, run the following goal:

```
$ ./mvnw package -Pnative
```

Then, you can run the app as follows:
```
$ target/spring-boot-native-example
```
