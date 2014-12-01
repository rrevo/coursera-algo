#!/bin/bash

set -x

javac -Xlint:unchecked InputParser.java Median.java && java -Xss64m -Xmx4g Median $1
