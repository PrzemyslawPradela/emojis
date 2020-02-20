## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
Service with emojis

## Technologies
Project is created with:
* Java EE version: 8
* Gson vesrion: 2.8.6
* Apache Commons IO version: 2.2
* Apache Derby version: 10.14.2
* Maven version: 3.3.9
* jQuery version: 3.4.1
* Bootstrap version: 4.3.1
* Font Awesome version: 5.12.1
* WideFly Application Server version: 18.0.1
* Apache NetBeans version: 11.2

## Setup
1. Install Java 8 Development Kit
2. Download and install Apache NetBeans 11.2
3. Download WideFly Application Server 18.0.1
4. Download and install [WideFly Plugin for NetBeans](http://plugins.netbeans.org/plugin/76472/wildfly-application-server)
5. [Add WideFly Application Server to NetBeans](http://www.mastertheboss.com/eclipse/jboss-netbeans/configuring-netbeans-with-wildfly)
6. Clone this repository
7. Download Apache Derby 10.14.2 (lib package) and [configure JavaDB in NetBeans](https://web.csulb.edu/~mopkins/cecs323/netbeans.shtml) 
  In *Database Location*: **_../emoji-service/derby_**
8. Open *emoji-api* and *emoji-client* in NetBeans
9. Build and deploy *emoji-api*
10. Build and deploy *emoji-client*
11. Open *emoji-client* in a web browser on *http//:localhost:8080/emojis*
