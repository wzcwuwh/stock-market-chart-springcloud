#!/usr/bin/env bash

#export BUILD_ID=dontKillMe, so project won't be killed by jenkins
export BUILD_ID=dontKillMe
#config param
#project path, when config path in execute shell, pwd can get this path
#export PROJ_PATH=`pwd`

cd $PROJ_PATH/stock-market-chart
mvn clean package

#stop springbootapplication
ps aux|grep "eureka"|grep -v grep|awk '{print $2}'| xargs kill -9

#start springbootapplication
cd $PROJ_PATH/stock-market-chart/stock-market-chart-eureka/target/
nohup java -jar stock-market-chart-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=server1
nohup java -jar stock-market-chart-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=server2
nohup java -jar stock-market-chart-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=server3