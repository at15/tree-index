#!/usr/bin/env bash

source ../export.sh

echo "index"
hadoop jar ../../indexer/target/indexer-0.0.1.jar --job index --base /user/at15/warehouse/ml-100k