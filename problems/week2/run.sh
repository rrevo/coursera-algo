#!/bin/bash

set -x

javac QuickSortMain.java QuickSortAlgo.java && java -Xss4m QuickSortMain $1
