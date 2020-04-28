#!/bin/bash
gradle build
docker build -f Dockerfile -t factory .