#!/bin/bash

set -x

javac -Xlint:unchecked TwoSum.java TwoSumConcurrent.java InputParser.java && java -Xss64m -Xmx4g TwoSumConcurrent $1
