## My Profile &nbsp;[![CircleCI](https://circleci.com/gh/pine/profile-website/tree/main.svg?style=shield)](https://circleci.com/gh/pine/profile-website/tree/main) [![codecov](https://codecov.io/gh/pine/profile-website/branch/main/graph/badge.svg)](https://codecov.io/gh/pine/profile-website)

## Requirements

- JDK 11 or later

## Libraries

- Spring Boot 2

## Getting started

```sh
$ ./gradlew :app:bootRun
```

## Deployment
[Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli) is required.

```sh
$ ./gradlew clean :app:bootJar

$ heroku apps:create your-app
$ heroku plugins:install java
$ heroku config:set SPRING_PROFILES_ACTIVE=prod
$ heroku config:set TZ=Asia/Tokyo
$ heroku config:set 'JAVA_OPTS==-Xmx200m -XX:+UseCompressedOops -XX:+UseStringDeduplication -XX:-OmitStackTraceInFastThrow -Dlog4j2.formatMsgNoLookups=true'
$ heroku deploy:jar --jar app/build/libs/app.jar --jdk 11 --app your-app
```

## Development
### JDK
For macOS users.

```
$ brew tap adoptopenjdk/openjdk
$ brew cask install adoptopenjdk11

$ java -version
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.2+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.2+9, mixed mode)
```

## License
MIT &copy; [Pine Mizune](https://profile.pine.moe/)
