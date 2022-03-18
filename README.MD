Orbeon Wrapper Docs
================

**Orbeon-Wrapper** platform, this creates a Java wrapper around **[Orbeon](https://www.orbeon.com/)** intended to customise
the look and actions of the orbeon form runner and builder

## <ins>Local Development</ins>

### Requirements
You must have access to this GitHub repo and the additional dependencies below (If any)

- **Docker >= 20.10.7**
- **Docker compose >= 1.29.2**
- **JDK == 11.0.14** used to build war archives for deployment

### Additional Requirements
- Access to the [Issuing platform repository](https://github.com/blueronin/issuing-platform) running in docker

### How to run this platform

- Pull from github

```shell
git clone https://github.com/blueronin/orbeon-wrapper
```

- run the project

```shell
cd orbeon-wrapper
docker-compose up
```

- This will start containers (orbeon, orbeon-db, orbeon-wrapper) running on docker network `orbeon_network`
- The wrapper will be available on `http://localhost:4569/orbeon-wrapper`
- Orbeon will be available on `http://localhost:4569/orbeon`

<hr style="border:2px solid red" />

Deployment Details and setup
----------------------------
Building for development or production.
- we utilize gradle properties to set active profiles at build time.
- Archives are build with a profile depending on the target environment.

Three `profiles` available from which to choose from (`local|dev|prod`)

To start the build run::

```shell
./gradlew -Pprofile=<profile> clean bootWar # profile can be one of local|dev|prod
```

The built archive is located under `build/libs/orbeon.wrapper-<version>.war`

#### <ins>Note</ins>
- **version** # This is set in `build.gradle.kts` Eg: `0.0.1-<profile>`
- This then creates a war archive in the format `orbeon.wrapper-0.0.1-dev.war`

Create a release/Tag on GitHub and attach the war archive as an artifact/asset, rename the asset to `orbeon-wrapper-<profile>.war`

> #### The Deployment process to respective environments is handled by the [setup-scripts](https://github.com/blueronin/setup-scripts/)
