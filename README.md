# tree-index

[![Build Status](https://travis-ci.org/at15/tree-index.svg?branch=master)](https://travis-ci.org/at15/tree-index)

B+ tree index for HDFS and Hive (experimental)

This project was originally from a course project in [sjtu cs433](https://github.com/at15/cs433/tree/master/tree-index).
The main purpose of this project is to learn index and Hadoop eco system better. I will try to struct the doc & links 
I found useful to help novice developer/student like me. The main content is listed below

- How to setup a local development environment using tarball on Mac & Ubuntu
- What is index, how index speed up query for traditional database
- B+ index, how to write a B+ index
- Hive and Hive index
- A dumb method to achieve B+ index for HDFS
- Custom index handler for Hive

It will have three applications

- a local B+ index & query
- B+ index & query for HDFS without using Hive
- B+ index & query for Hive

## License

This project is licensed under Apache2.0, following is some of the open source libraries it uses

- Apache2.0 [mellowtech-core](https://github.com/msvens/mellowtech-core) B+ index

