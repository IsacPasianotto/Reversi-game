
![Static Badge](https://img.shields.io/badge/Java-Java?logo=Oracle&logoColor=%23F80000&color=%23343434)
![Static Badge](https://img.shields.io/badge/OpenJDK-OpenJDK?logo=OpenJDK&logoColor=white&color=%23437291)
![Static Badge](https://img.shields.io/badge/gradle-gradle?logo=gradle&color=%2302303A)&nbsp;
![Static Badge](https://img.shields.io/badge/Apache%20Groovy-groovy?logo=Apache%20Groovy&logoColor=white&color=%234298B8)&nbsp;
![Static Badge](https://img.shields.io/badge/JUnit5-JUnit5?logo=JUnit5&logoColor=white&color=%2325A162)&nbsp;

![Static Badge](https://img.shields.io/badge/CircleCI%20stautus:-circleci?logo=circleci&color=%23343434)
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/Uefri8ookENmbrruKUV1N9/8JfxiuKMjSWcSFbqKyzDMp/tree/main.svg?style=svg&circle-token=710d38a05770bb7e6b2566c1e1fdf6216931c016)](https://dl.circleci.com/status-badge/redirect/circleci/Uefri8ookENmbrruKUV1N9/8JfxiuKMjSWcSFbqKyzDMp/tree/main)




# Reversi-game

This repo was made for the "Software Development Methods" exam at the University of Trieste that I took during my
studies in Data Science and Scientific Computing.

The project assignment consists in implementing a game, practicing Continuous integration and using automated tests and Test Driven
Development to grow the code, and using the Agile methodology to manage the project in a group work setting (The project was done with [Davide Rossi](https://github.com/DavideRossi1/)).

The implemented game is the classic [Reversi](https://en.wikipedia.org/wiki/Reversi) game, also known as Othello.


The project is built with [Gradle](https://gradle.org/), and programed in `Java`.

## Project structure

The project is divided into tree main modules:

- `core`: contains the game logic and the model of the game and serves as a library for the other two modules.
- `terminal`: contains the terminal version of the game.
- `desktop`: contains the desktop version of the game.

## Requirements

The project requires `Java` to be installed on your system. Check the [troubleshooting](#troubleshooting) section for more information.

Gradle is not required to be installed on your system, as the project uses the gradle wrapper.


## How to run the project

The easiest way to run the project is to download the latest release from the [releases page](https://github.com/DavideRossi1/Reversi-game/releases/tag/v1.2) and run the `reversi.jar` file:

```
$ java -jar <fileName>.jar
```

Since the project is built with gradle, you can also rely on the gradle wrapper to build, test and run the project from the source code.  In particular, for each module listed [above](#project-structure), you can run the following commands:

- `./gradlew <terminal|desktop>:run`: to play the game.
- `./gradlew <module>:dist`: to create a distributable package of the module in the `./readyToDistribute` directory.
- `./gradlew <module>:build`: to build the module.
- `./gradlew <module>:test`: to run the tests of the module.

Moreover, you can run:

- `./gradlew dist` to create a distributable package of both the terminal and the desktop version of the game in the `./readyToDistribute` directory.
- `./gradlew build` to build the whole project.
- `./gradlew test` to run all the tests of the project.
- `./gradlew clean` to clean all the files that have been generated. 

Just for example, to run the graphical version of the game, you can run the following command:

```
$ ./gradlew desktop:run
```

***Notes***:

- It is suggested to run the terminal version of the game with `./gradlew terminal:run --console=plain` to avoid some issues with the gradle output.
- Running the tests for the `desktop` module (hence also the `build` task) can cause a temporary flickering of the screen, as each test will open one or more frames.\
    To avoid it you can use the [`xvfb`](https://linux.die.net/man/1/xvfb) tool, which is a virtual framebuffer for X server, to run the tests in a virtual display. For example: `xvfb-run ./gradlew desktop:test`.

## Documentation:

The project is documented with the [javaDoc](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html) tool. Usually running the gradle task `javadoc` will create the documentation in the `./build/docs/javadoc` directory.
However, since the project is divided into three modules, the `javadoc` task will create the documentation for each module separately. We have provided a custom task to copy all the documentation in the distribution directory.

To do that you can run the following command:

```
$ ./gradlew mergeDocs
```

This will create the documentation in the `./readyToDistribute/javaDoc` directory.

**Remark:** this was tested exclusively in Java 21.

## Troubleshooting

#### 1. How to check the version of Java installed on your system:

This project requires `Java 17` or later to be installed on your system. Be sure that the output of the `java --version` command is something like:

```
$ java --version
openjdk 17.0.9 2023-10-17
OpenJDK Runtime Environment (Red_Hat-17.0.9.0.9-4) (build 17.0.9+9)
OpenJDK 64-Bit Server VM (Red_Hat-17.0.9.0.9-4) (build 17.0.9+9, mixed mode, sharing)
```
or later, for example:
```
$ java --version
openjdk 21.0.2 2024-01-16
OpenJDK Runtime Environment (Red_Hat-21.0.2.0.13-1) (build 21.0.2+13)
OpenJDK 64-Bit Server VM (Red_Hat-21.0.2.0.13-1) (build 21.0.2+13, mixed mode, sharing)
```

In principle, the project should be compatible with any version of Java older than `14` (Version in which the `record` feature was introduced), but this assumption is not tested.
The only tested versions are the successive LTS versions of Java, i.e. `17` and `21`.

#### 2. Gradle wrapper and Java 21:

Apparently under some conditions the gradle wrapper ignores the `targetCompatibility` and `sourceCompatibility` settings in the `build.gradle` when more than one version of Java is installed on the system.

At least for Unix systems (we didn't have the chance to test it on Windows), a workaround is to manually set the `JAVA_HOME` environment variable:
```
$ export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```
where `/usr/lib/jvm/java-21-openjdk-amd64` is the path to the JDK installation on your system.

#### 3. Wayland and Xwayland

If you are a linux user and use [`Wayland`](https://wayland.freedesktop.org/) as display server, to run the desktop version of the application you will need [`Xwayland`](https://wayland.freedesktop.org/xserver.html) installed on your system.


## Credits and attributions

The complete list of all the credits, attributions and licenses can be found in the file [`ATTRIBUTIONS.md`](./ATTRIBUTIONS.md) file.

## Appendx:

This repository is obtained mirroring the [Davide's one](https://github.com/DavideRossi1/Reversi-game), check it out to see all failures and successes in the tests commit by commit.
