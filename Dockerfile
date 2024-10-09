FROM openjdk:17-alpine

VOLUME /tmp

ADD ./servicio-oauth.jar  servicio-oauth.jar

EXPOSE 9100

ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]
