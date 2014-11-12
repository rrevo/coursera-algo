#!/bin/bash

set -x

javac -Xlint:unchecked ShortestPath.java InputParser.java Graph.java && java -Xss64m -Xmx4g ShortestPath $1
