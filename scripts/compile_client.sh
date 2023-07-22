#!/bin/bash

# use run directory as runtime classpath

RUNDIR="$(pwd)/run"

# initialize run directory
if [ ! -d ${RUNDIR} ]; then
    mkdir ${RUNDIR}

    # compile javax classes
    javac ./source/javax/json/*.java -d ${RUNDIR} -classpath .

    # compile menu classes
    javac ./source/ConsoleMenu/*.java -d ${RUNDIR} -classpath .

fi

# compile client classes
javac ./source/client/*.java -d ${RUNDIR} -classpath .