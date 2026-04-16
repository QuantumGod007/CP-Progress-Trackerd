#!/bin/bash

# Download MySQL JDBC Driver
echo "Downloading MySQL JDBC Driver..."

cd lib

# Download MySQL Connector/J 8.0.33
curl -L -o mysql-connector-java-8.0.33.jar \
  https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar

if [ -f "mysql-connector-java-8.0.33.jar" ]; then
    echo "✓ MySQL JDBC Driver downloaded successfully!"
    ls -lh mysql-connector-java-8.0.33.jar
else
    echo "✗ Download failed!"
    exit 1
fi
