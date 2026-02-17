#!/usr/bin/env bash
set -euo pipefail

# set new version of maven
if [[ $# -ge 1 ]]; then
    mavenVersion="$1"
    echo "[ INFO] Using version from command line: ${mavenVersion}"
else
    read -rp "[ INFO] Enter Apache Maven version (e.g. 3.9.11): " mavenVersion
fi
echo

if [[ -z "${mavenVersion}" ]]; then
    echo "[ERROR] No version provided. Exiting."
    exit 1
fi

# update version
echo "[ INFO] Updating Maven Wrapper to version: '${mavenVersion}'"
./mvnw wrapper:wrapper -Dtype=only-script -Dmaven="${mavenVersion}"

echo
echo "[ INFO] Update complete. Please review changes and commit them as needed."
