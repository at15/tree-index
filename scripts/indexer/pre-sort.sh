#!/usr/bin/env bash

source ../export.sh

echo "pre-sort"
hadoop jar ../../indexer/target/indexer-0.0.1.jar --job pre-sort --base /user/at15/warehouse/ml-100k