FROM mislbd/oracle-java:8u66

ARG BUILD_ID
ARG VERSION
ARG ARTIFACT

COPY ${ARTIFACT} /opt/app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:MaxMetaspaceSize=128m"
ENV BUILD_NUMBER ${BUILD_ID}
ENV ARTIFACT_VERSION ${VERSION}
ENV CONFIG_PROFILES="port,stage,misl,core,asset"
ENV CONFIG_SERVER_URI="http://172.16.190.20:9999"

WORKDIR /opt

CMD java ${JAVA_OPTS} -Duser.timezone=Asia/Dhaka -jar app.jar --spring.profiles.active=$CONFIG_PROFILES --spring.cloud.config.uri=$CONFIG_SERVER_URI