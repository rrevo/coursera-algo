#!/bin/bash

set -x

javac Inversions.java

java Inversions $1
