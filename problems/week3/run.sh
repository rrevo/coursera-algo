#!/bin/bash

set -x

javac -Xlint:unchecked MinCut.java InputParser.java Graph.java && java -Xss4m MinCut $1
