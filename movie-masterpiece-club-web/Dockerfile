# FROM : 생성할 이미지의 베이스 이미지 설정
# MAINTAINER : 이미지를 생성한 개발자의 정보 및 일반적으로 메일정보를 기입
FROM            openjdk:8-jre
MAINTAINER      pasudo123 <pasudo123@naver.com>

# ARG       : 변수 지정
# COPY      : {source} {dest} 로 옮김 설정
# WORKDIR   : 특정 DIR 에서 작업 수행 설정
# CMD       : WORKDIR 에서 명령어 수행
ARG             DEPLOY_PATH=/data/home/common/deploy/www/gulagbu
COPY            ./build/libs/movie-masterpiece-club-web-*.jar /data/home/common/deploy/www/gulagbu/
WORKDIR         /data/home/common/deploy/www/gulagbu
CMD             ['java', '-jar', '-Dspring.profiles.active=prod', '${DEPLOY_PATH}/movie-masterpiece-club-web-*.jar']

# EXPOSE    : 호스트와 연결할 포트를 설정
EXPOSE 8080