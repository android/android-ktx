#!/bin/bash

set -e

echo -n "Cloning…"

if [ ! -d build/fake-aosp/tools/metalava ]; then
    git clone -q https://android.googlesource.com/platform/tools/metalava/ build/fake-aosp/tools/metalava
fi

(
    cd build/fake-aosp/tools/metalava

    # Update in case the repo was already cloned.
    git pull -q
    echo " Done"

    echo -n "Building…"
    ./gradlew jar --console=plain -q --no-daemon
    cp ../../out/host/common/libs/metalava-*.jar ../../../../metalava.jar
    echo " Done"

    echo -e "\nDependencies:\n"
    ./gradlew dependencies --no-daemon --configuration implementation | \egrep '^.--- ' | cut -d' ' -f2
)
