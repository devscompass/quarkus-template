# Quarkus Template

[![Build](https://img.shields.io/github/actions/workflow/status/zbhavyai/quarkus-template/build.yml?label=Build)](https://github.com/zbhavyai/quarkus-template/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/actions/workflow/status/zbhavyai/quarkus-template/release.yml?label=Release)](https://github.com/zbhavyai/quarkus-template/actions/workflows/release.yml)
[![License](https://img.shields.io/github/license/zbhavyai/quarkus-template?label=License)](https://github.com/zbhavyai/quarkus-template/blob/main/LICENSE)

A **starter template** for building backend applications with [Quarkus](https://quarkus.io/), the _supersonic-subatomic_ Java framework.

## :sparkles: Tech Stack and Features

- :zap: [Quarkus](https://quarkus.io/), the supersonic Java framework with fast boot, hot reload, and cloud-native design
- :cyclone: Reactive programming with Quarkus built-in support for [Mutiny](https://smallrye.io/smallrye-mutiny)
- :gear: [Hibernate Reactive](https://hibernate.org/reactive/) for fully non-blocking database access
- :elephant: [PostgreSQL](https://www.postgresql.org/) as the production-ready relational database
- :luggage: [Flyway](https://www.red-gate.com/products/flyway/community/) for version-controlled database migrations
- :open_file_folder: Defined project structure with ready-to-use [`application.properties`](src/main/resources/application.properties)
- :test_tube: [JUnit](https://junit.org/) unit tests to keep code honest
- :chart_with_upwards_trend: [Micrometer](https://micrometer.io/) for application metrics and observability
- :fast_forward: [Develocity build cache](https://gradle.com/develocity/product/build-cache/) enabled for accelerated builds
- :hammer_and_wrench: [Makefile](https://www.gnu.org/software/make/) targets for format, build, run, and container tasks
- :broom: [Spotless](https://github.com/diffplug/spotless) on `validate` phase and with a `pre-commit` hook for style enforcement
- :pen: [VS Code](https://code.visualstudio.com/) settings included, [IntelliJ IDEA](https://www.jetbrains.com/idea/) configs [here](https://github.com/zbhavyai/fedora-setup/tree/main/roles/intellij_idea/files)
- :cloud: [Firebase Studio](https://firebase.studio/) settings for cloud-based development
- :art: [google-java-format](https://github.com/google/google-java-format) for consistent code formatting
- :page_facing_up: [.editorconfig](https://editorconfig.org/) for consistent coding styles across editors
- :whale: Containerization with Dockerfile and docker compose
- :otter: Automatic [Podman](https://podman.io/)/[Docker](https://www.docker.com/) detection for local dev
- :vertical_traffic_light: [GitHub Actions](https://github.com/features/actions) and [Bitbucket Pipelines](https://www.atlassian.com/software/bitbucket/features/pipelines) for CI/CD
- :label: Artifact versioning based on Git SHA or tag, both in CI/CD and local builds
- :robot: [Dependabot](https://github.com/dependabot) for automated dependency updates

## :rocket: Getting started

- Before you start development on this project, run the prep target. This will install a hook that would check your commit for code formatting issues.

   ```shell
   make prep
   ```

- Run the application in dev mode that enables live coding. Quarkus dev UI would be accessible at [http://127.0.0.1:8080/q/dev-ui/welcome](http://127.0.0.1:8080/q/dev-ui/welcome).

   ```shell
   make dev
   ```

- To format the code, enable [google-java-format](https://github.com/google/google-java-format) and in your IDE and turn on the auto-formatting on save. You may also run spotless plugin manually.

   ```shell
   make format
   ```

## :package: Packaging and running

1. Build the application

   ```shell
   make build

   # OR

   make container-build
   ```

2. Run the application

   ```shell
   make run

   # OR

   make container-run
   ```

## :page_facing_up: License

The Quarkus Template is licensed under the terms of the [MIT license](LICENSE).
