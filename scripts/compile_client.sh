#!\bin\bash

# use CnC top as classpath
CLASSPATH=.

RUNDIR="$(pwd)\run"
SOURCEDIR="$(pwd)\source"

if [ ! -d ${RUNPATH} ]; then
    mkdir ${RUNPATH}
fi

# compile javax classes 
if [ ! -d ${RUNPATH}\javax ]; then
    mkdir ${RUNPATH}\javax\json
    mkdir ${RUNPATH}\javax\json\spi
    mkdir ${RUNPATH}\javax\json\stream
    javac .\source\javax\json\*.java -d ${RUNPATH}\javax\json
    javac .\source\javax\json\spi\*.java -d ${RUNPATH}\javax\json\spi
    javac .\source\javax\json\stream\*.java -d ${RUNPATH}\javax\json\stream
fi

# compile menu classes
if [ ! -d ${RUNPATH}]
