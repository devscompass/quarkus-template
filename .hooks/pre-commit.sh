#!/usr/bin/env bash
set -euo pipefail

function block() {
    echo -e "\n\n"
    echo "$@"
    echo "[ERROR] Commit blocked."
    exit 1
}

CHECKS="format_checks"

function format_checks() {
    mapfile -d '' -t staged_java < <(git diff --cached --name-only -z --diff-filter=ACMR -- '*.java' || true)

    if ((${#staged_java[@]} == 0)); then
        return 0
    fi

    if ! ./mvnw --quiet --batch-mode spotless:check; then
        block "[ERROR] Formatting checks failed; run 'make format' to fix."
    fi
}

for CHECK in $CHECKS; do
    ($CHECK) || exit $?
done
