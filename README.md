# Zabu Client (W.I.P.)
FOSS Minecraft client for versions starting from 1.8.9 all the way to 1.20.2

## Features
- [x] Multiversion support (currently 1.8.9 - 1.20.2)
- [ ] Fabric support
- [ ] Forge support
- [x] Epic anti-aliased GUI thanks to NanoVG

## How it works
### Components
There are 3 components required for the client to work:
- The client core, which is the main client code
- The JavaAgent, responsible for attaching the client to the game
- Version-specific code, responsible for bridging the client core and the game

### Building
The building process is split into 3 steps:
1. Building the Core, JavaAgent and Version-specific code into separate jars
2. Extracting each jar (Core first, then version-specific code and last of all, the JavaAgent) 
into the same directory
3. Zipping the directory into a single jar

### Running
If the building process above is followed, the client can be run by simply adding the following JVM arguments:
```
-javaagent:/path/to/zabu/agent.jar
```
Otherwise, the client Core and Version jars must be added to the classpath, and the
`-javaagent` argument must be added with the path to the JavaAgent jar.

## License
This project is licensed under the GPLv2 license. See [LICENSE](./LICENSE)