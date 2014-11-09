#!/bin/bash

set -x

javac -Xlint:unchecked SCCKosaraju.java InputParser.java Graph.java && java -Xss64m -Xmx4g SCCKosaraju $1
