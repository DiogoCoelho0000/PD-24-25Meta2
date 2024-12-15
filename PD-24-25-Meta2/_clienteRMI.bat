@echo off
MODE 100,50
java -cp . src/main/java/pt/isec/pd/rmi/cliente/RMIClient.java 127.0.0.1 123
pause