# [FROM]           :: 생성할 이미지의 베이스 이미지 설정
# [MAINTAINER]     :: 이미지를 생성한 개발자의 정보 및 일반적으로 메일정보를 기입 (@Deprecated)
# [LABEL]          ::
# [ARG]            :: 변수 지정
# [ADD]            :: {source} {dest} source 에 있는 jar 파일이 dest 에 있는 이름으로 이미지에 적재됨을 설정
# [ENTRYPOINT]     :: 이미지가 컨테이너화 되면서 실행될 명령어를 입력, (배열형태 기입 및 적재된 이미지 실행 위치 기입)

FROM            openjdk:8-jre-alpine
LABEL           maintainer="pasudo123 <pasudo123@naver.com>"
ARG             JAR_FILE=movie-masterpiece-club-web-*.jar
ENV             SPRING_PROFILE=prod
ADD             ${JAR_FILE} target/movie-masterpiece-club-web.jar
ENTRYPOINT      ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILE}", "target/movie-masterpiece-club-web.jar"]

# EXPOSE    : 호스트와 연결할 포트를 설정
EXPOSE 8080

## 참고링크
## https://swiftymind.tistory.com/91

# [CMD]
# (1) 컨테이너가 시작될때 실행
# (2) Dockerfile 에서 한번만 사용가능

# [ENTRYPOINT]
# (1) [CMD] 과 동일하게 컨테이너가 시작될때 실행
# (2) [CMD] 과 같이 있으면 ENTRYPOINT 는 실행파일, CMD 는 매개변수 역할을 함.

# [ADD]
# (1) 파일복사
# (2) 압축파일인 경우 압축을 품(단, URL로 가져온 파일은 압축만 해제하고 풀지는 않음)
