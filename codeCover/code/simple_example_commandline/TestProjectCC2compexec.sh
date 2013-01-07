# TestProjectCC2compexec.sh
javac -cp "TestProject/instrumentedSrc" TestProject/instrumentedSrc/main/Main.java
cd "TestProject/instrumentedSrc"
java main.Main
cd ../..
