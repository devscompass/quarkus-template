CONTAINER_ENGINE := $(shell if command -v podman >/dev/null 2>&1; then echo podman; else echo docker; fi)
REVISION := $(shell git rev-parse --short HEAD)

.PHONY: help init clean test dev format check-updates build build-native run run-native container-build container-run container-stop container-logs container-destroy

help: ## show this help message
	@echo "Available targets:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-20s - %s\n", $$1, $$2}'

init: ## install git hook
	@ln -sf $(CURDIR)/.hooks/pre-commit.sh .git/hooks/pre-commit
	@echo "Hook installed";

clean: ## clean build artifacts
	@./mvnw --quiet --batch-mode clean;

test: ## run tests
	@./mvnw clean test -Drevision=$(REVISION);

dev: ## start app in development mode
	@./mvnw clean quarkus:dev

format: ## format codebase
	@./mvnw spotless:apply

check-updates: ## check for dependency and extension updates
	@./mvnw versions:display-property-updates
	@./mvnw versions:display-extension-updates

build: ## build app in jvm mode
	@./mvnw clean verify -Drevision=$(REVISION)

build-native: ## build app in native mode
	@./mvnw clean verify -Dnative -Drevision=$(REVISION)

run: ## run app in jvm mode
	@java -jar ./target/quarkus-template-*-runner.jar

run-native: ## run app in native mode
	@./target/quarkus-template-*-runner

container-build: ## build and create app container image
	@REVISION=$(REVISION) $(CONTAINER_ENGINE) compose build

container-run: ## run app container
	@REVISION=$(REVISION) $(CONTAINER_ENGINE) compose up --detach

container-stop: ## stop app container
	@REVISION=$(REVISION) $(CONTAINER_ENGINE) compose down

container-logs: ## print app container logs
	@REVISION=$(REVISION) $(CONTAINER_ENGINE) compose logs --follow

container-destroy: ## stop and delete app container, networks, volumes, and images
	@REVISION=$(REVISION) $(CONTAINER_ENGINE) compose down --volumes --rmi local
