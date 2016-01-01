#!/usr/bin/env bash

source ../export.sh

echo "mkdir"

hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/at15
hdfs dfs -mkdir /user/at15/warehouse
hdfs dfs -mkdir /user/at15/warehouse/ml-100k
hdfs dfs -mkdir /user/at15/warehouse/ml-100k/src
