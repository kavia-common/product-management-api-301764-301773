#!/bin/bash
cd /home/kavia/workspace/code-generation/product-management-api-301764-301773/product_api_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

