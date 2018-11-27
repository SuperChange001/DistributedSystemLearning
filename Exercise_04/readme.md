# Exercise 04
## Assignment 1: Serialization in Java
> In this assignment you will test the binary object and XML serialization mechanisms provided by Java.

1. Compare the execution times of binary object and XML serialization. Wire the serializers to a java.io.ByteArrayOutputStream. What is the execution time of 100 serializations when using binary object or XML serialization?
```
    After excute SerializationTimeTest.java, We get the log information in the console window:

    Execution times of binary object serialization: 3144ms.

    Execution times of XML serialization:258ms
```
2. Use a java.io.FileOutputStream to write the serialization result to disk. What are the file sizes?
```
    After excute SerializationFileSizeTest.java, We get find the files in Exercise_04/out/
    binary_object_serialization.bin ----    34 KB
    xml_serialization.bin           ----    34 KB
```