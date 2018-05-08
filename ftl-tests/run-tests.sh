#!/bin/sh

# Run tests on test lab
gcloud firebase test android run \
    --type instrumentation \
    --app ftl-tests/dummy.apk \
    --test build/outputs/apk/androidTest/debug/core-ktx-debug-androidTest.apk \
    --device model=Nexus6P,version=27,locale=en_US,orientation=portrait \
    --timeout 30m \
    --results-bucket cloud-test-android-devrel-ci \
    --no-record-video \
    --no-performance-metrics
