#!/bin/sh
mvn clean package && docker build -t de.shwiri/online-shop .
docker rm -f online-shop || true && docker run -d -p 8050:8080 -p 4848:4848 --name online-shop de.shwiri/online-shop
