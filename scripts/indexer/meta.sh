#!/usr/bin/env bash

source ../export.sh

echo "meta"
hadoop jar ../../indexer/target/indexer-0.0.1.jar --job meta --base /user/at15/warehouse/ml-100k