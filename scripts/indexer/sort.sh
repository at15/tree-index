#!/usr/bin/env bash

source ../export.sh

echo "sort"
hadoop jar ../../indexer/target/indexer-0.0.1.jar --job sort --base /user/at15/warehouse/ml-100k