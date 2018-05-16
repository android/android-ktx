#!/bin/sh

TEST_DIR=$1

# Create directory for results
mkdir "$TEST_DIR"

# Pull down test results
gsutil -m cp -r -U "`gsutil ls gs://cloud-test-android-devrel-ci | tail -1`*" "$TEST_DIR"
