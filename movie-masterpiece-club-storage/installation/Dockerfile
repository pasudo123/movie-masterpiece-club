###########################################################################
# FROM          : 생성할 이미지의 베이스 이미지 정보
# EXPOSE        : 호스트와 연결할 포트를 설정, 도커 컨테이너 포트
#
#
#
#
#
#
#
###########################################################################

FROM            openjdk:8-jre-alpine
MAINTAINER      pasudo123 <pasudo123@naver.com>
ARG             JAR_FILE=movie-masterpiece-club-storage-*.jar
ENV             SPRING_PROFILE=prod
ADD             ${JAR_FILE} target/movie-masterpiece-club-storage.jar
ENTRYPOINT      ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILE}", "target/movie-masterpiece-club-storage.jar"]

EXPOSE 8080
