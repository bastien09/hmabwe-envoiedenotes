#!/bin/bash
# depuis le rep du projet
echo "Compiling...";
mkdir bin;
rm -Rf bin/*;
mkdir dist;
rm -Rf dist/*;
cd src;
javac -d ../bin -classpath .:../lib/activation.jar:../lib/junit-4.10.jar:../lib/mail.jar:../lib/jxl.jar:../lib/ojdbc6.jar:../lib/eclipselink.jar:../lib/javax.persistence_2.0.3.v201010191057.jar -sourcepath . fr/unice/hmabwe/vue/FenetrePremiere.java;
mkdir ../bin/resource;
mkdir ../bin/META-INF;
cp META-INF/MANIFEST.MF ../bin/META-INF/;
cp -R resource ../bin/resource;
cd ../bin;
jar cmf META-INF/MANIFEST.MF EnvoiDeNotes.jar .
cd ..;
mv bin/EnvoiDeNotes.jar dist/;
