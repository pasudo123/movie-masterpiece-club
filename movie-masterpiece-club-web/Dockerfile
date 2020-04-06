# FROM : 생성할 이미지의 베이스 이미지 설정
# MAINTAINER : 이미지를 생성한 개발자의 정보 및 일반적으로 메일정보를 기입

FROM            openjdk:8-jre-alpine
MAINTAINER      pasudo123 <pasudo123@naver.com>

# ARG           : 변수 지정
# COPY          : {source} {dest} 로 옮김 설정
# ADD           : {source} {dest} source 에 있는 jar 파일이 dest 에 있는 이름으로 이미지에 적재됨을 설정
# ENTRYPOINT    : 이미지가 컨테이너화 되면서 실행될 명령어를 입력
# WORKDIR       : 특정 DIR 에서 작업 수행 설정
# CMD           : WORKDIR 에서 명령어 수행

ARG             JAR_FILE=movie-masterpiece-club-web-*.jar
ADD             ${JAR_FILE} movie-masterpiece-club-web.jar
ENTRYPOINT      ["java", "-jar", "/movie-masterpiece-club-web.jar"]
#ARG             DEPLOY_PATH=/data/home/common/deploy/www/gulagbu
#COPY            ./build/libs/movie-masterpiece-club-web-*.jar /data/home/common/deploy/www/masterpiece/
#WORKDIR         /volumn1/docker/deploy/www/masterpiece
#CMD             ["java", "-jar", "-Dspring.profiles.active=prod", "/movie-masterpiece-club-web.jar"]

# EXPOSE    : 호스트와 연결할 포트를 설정
EXPOSE 8080

## 참고링크
## https://swiftymind.tistory.com/91