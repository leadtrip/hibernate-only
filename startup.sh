#!/bin/bash

function buildProject () {
    echo assembling...
    ./gradlew assemble
}

function composeDown() {
  echo decomposing...
  docker-compose down
}

function composeUp() {
  echo composing...
  docker-compose up --build --force-recreate -d
}

function runApp() {
  echo running...
  ./gradlew run
}

function __run__() {
  composeDown
  buildProject
  composeUp $@
  #runApp
}

__run__ $@
