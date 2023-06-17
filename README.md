# Zabu (WIP)
A multi-version Minecraft client. 

## Roadmap
- [x] Multi version support (Currently only 1.8.9 and 1.19.4)
- [x] Mixins
- [ ] UI Framework
- [ ] Social features
    - [ ] Friends
    - [ ] Parties
    - [ ] Chat / DMs / Group chats
    - [ ] World hosting and joining
- [ ] Editable HUD
- [ ] Configurable keybinds
- [ ] Multiple build targets
    - [x] Vanilla (Obfuscated environment)
    - [ ] [Forge](https://files.minecraftforge.net/) (SRG Mappings)
    - [x] [Fabric](https://fabricmc.net/) (Intermediary Mappings)
    - [ ] [Lunar Client](https://lunarclient.com/) (Custom mappings)
    - [ ] [Badlion Client](https://client.badlion.net/) (Custom mappings)
    - [ ] Others?

## How to add support for a new version
1. Copy a pre-existing version folder
    - `1.19.4` folder for versions 1.13 and onwards
    - `1.8.9` folder for versions 1.7 (Maybe lower?) to 1.12.2
2. Rename the folder to the version you want to add support for
3. Change the `version` variable in `build.gradle` to the version you want to add support for
4. Add the version to the `versions` array in `settings.gradle`
5. Make sure the mappings match up, you can always use your own mappings as long as loom supports it
6. The folder structure should look like this:
```
├── build.gradle
└── src/
    └── main/
        ├── java/
        │   └── <your package>/
        │       ├── <the version name in word form (e.g oneeightnine)>/
        │       │   ├── mixins/
        │       │   │   └── <Mixins go here>
        │       │   └── <any interfaces in order to share the correct methods to the Core>
        │       └── start/
        │           └── VersionMain.java
        └── resources/
            └── client.mixins.json
```

## Running
1. Run the `start_<version>` run configuration

## Building and using in a production environment
1. Run the `merge_<version>` run configuration
2. The required file is in `build/<version>_merged.jar`
3. Add the jar as a javaagent when Minecraft is launching
    - `-javaagent:<path to jar>`

## Adding new methods for the core to use
1. Create a new interface in the version package
2. Add the methods you want to share with the core
3. Make sure the interface is implemented in the versions that support it

## License
This project is licensed under the [GPL v3](./LICENSE) license.
