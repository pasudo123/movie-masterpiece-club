#!/bin/bash
# 쉘 스크립트 파일의 첫 라인은 `#!/bin/bash` 로 시작

###############################
# {variable}={data} : 변수 선언
# ${variable} : 변수 사용
###############################

HOST_NAME=GuLagBu
BASE_PATH=/data/home/www/gulagbu.com
LATEST_JAR_FILE=$(ls -lc ${BASE_PATH}/*.jar | tail -n 1)    # 최근 변경 시간을 기준으로 하나의 jar

echo "> $HOST_NAME Deploy Start."

echo "> $LATEST_JAR_FILE Build."

