FROM anapsix/alpine-java
MAINTAINER "PikaQiu"<xxxx@xx.com>
VOLUME /work
ADD pikaqiu-family-bucket*.jar app.jar
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar