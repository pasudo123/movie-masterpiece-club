#!/bin/sh

latest_jar=$(ls -dt movie* | sort -V  | tail -n 1)
tag_name="latest"
container_name="masterpiece-club-1st"

############################################################
# [docker build command]
############################################################
image_build_command="sudo docker build --no-cache --tag $latest_jar:$tag_name --build-arg JAR_FILE=$latest_jar ."
printf "=================================================\n" 
printf "| Docker image build !!\n"
printf "=================================================\n" 
printf "| JAR_FILE :: $latest_jar\n"
printf "| TAG_NAME :: $tag_name\n"
printf "=================================================\n"
printf "| $image_build_command\n"
printf "=================================================\n"

$image_build_command


############################################################
# [stop container by name or container-id]
############################################################
stop_container_command="sudo docker stop $container_name"
printf "\n"
printf "=================================================\n"
printf "| $stop_container_command\n"
printf "=================================================\n"

$stop_container_command


############################################################
# [remove container by name or container-id]
############################################################
remove_container_command="sudo docker rm $container_name"
printf "\n"
printf "=================================================\n"
printf "| $remove_container_command\n"
printf "=================================================\n"

$remove_container_command

############################################################
# [docker container run command]
############################################################
container_run_command="sudo docker run -d -p 9090:8080 --name $container_name --mount type=volume,source=test-vol,target=/static/images $latest_jar:$tag_name"
printf "\n"
printf "=================================================\n"
printf "| Docker container running !!\n"
printf "=================================================\n"
printf "| $container_run_command\n"
printf "=================================================\n"

$container_run_command
