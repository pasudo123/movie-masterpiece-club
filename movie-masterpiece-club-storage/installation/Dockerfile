FROM            openjdk:8-jre-alpine
MAINTAINER      pasudo123 <pasudo123@naver.com>
ARG             JAR_FILE=movie-masterpiece-club-storage-*.jar
ADD             ${JAR_FILE} target/movie-masterpiece-club-storage.jar
ENTRYPOINT      ["java", "-jar", "target/movie-masterpiece-club-storage.jar"]

# EXPOSE    : 호스트와 연결할 포트를 설정 : 도커 컨테이너 포트.
EXPOSE 28080