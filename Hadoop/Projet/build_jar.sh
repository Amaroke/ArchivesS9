hadoop com.sun.tools.javac.Main -d . $(find src -name "*.java")
jar cf MyHadoopApps.jar *.class
rm *.class
