![Image of VapeCloud](https://i.ibb.co/yNHfskr/logo-text.png)
This is the **VapeCloud** project, it is a Minecraft Dynamic CloudSystem based on Netty.

**IMPORTENT: this Cloudsystem is still in Develop**


## Requirements
 * Java 8
 * Linux/Windows server with a minimum of 2GB DDR3 Memory and 2 vCores
 * Internet connection

 **The use of Linux containers (LXC) or OpenVZ containers (OVZ) is discouraged. There are many issues with their stability.**  
Use of KVM virtualization or dedicated servers is recommended.


## Features
- Plug & Play
- **Module System** (SmartProxy, Loadbalancer, Sign, Permission)
- Dynamic / Static Server
- Multiroot & MultiProxy
- Ingame Commands
- Auto-Starting servers
- A powerful API
- MultiGroup system
- Colored Console


## Maven:
```xml

    <repositories>
        <repository>
            <id>VapeCloud-repo</id>
            <url>https://vape.cloud/repositories</url>
        </repository>
    </repositories>

    <dependencies>
        <!-- Spigot/BungeeCord -->
        <dependency>
            <groupId>cloud.vape</groupId>
            <artifactId>vapecloud-api</artifactId>
            <version>{VERSION}</version>
            <scope>provided</scope>
        </dependency>
     </dependencies>

```



## Support
- Discord: /
- Twitter: @VapeCloudService
- website: https://vape.cloud/   (**Maintenance**)
