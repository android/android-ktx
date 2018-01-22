#!/bin/bash
#
# Creates a zip file in the format required for uploading to maven.google.com.
#
# Note: If you're on Mac OS you'll need md5sum and sha1sum which can be installed with Homebrew via:
# brew install md5sha1sum
#
# TODO migrate this all to Gradle tasks

set -ex

rm -r build/google-zip || true
rm build/google.zip || true

mkdir build/google-zip
cp build/libs/* build/google-zip/
cp build/outputs/aar/*-release.aar build/google-zip/

for f in build/google-zip/*; do
  md5sum "$f" > "$f.md5"
  sha1sum "$f" > "$f.sha1"
done

zip -0 -r -j build/google.zip build/google-zip
