#!/usr/bin/env bash

source ../export.sh

echo "remove old"
hdfs dfs -rm /user/at15/warehouse/ml-100k/src/*

echo "upload new"
hdfs dfs -put ../../data/ml-100k/u.user /user/at15/warehouse/ml-100k/src