Releasing
=========

 1. Change the version in `build.gradle` to a non-SNAPSHOT version (e.g., `0.2-SNAPSHOT` -> `0.2`)
 2. Update the `CHANGELOG.md` for the impending release.
 3. Update the `README.md` with the new version.
 4. Copy `api/current.txt` to `api/X.Y.Z.txt`.
 5. `git commit -am "Prepare for release X.Y.Z."` (where X.Y.Z is the new version)
 6. `./gradlew clean assemble`
 7. Upload the ZIP in `build/distributions/` to Google's maven repository.
 8. `git tag -a X.Y.X -m "Version X.Y.Z"` (where X.Y.Z is the new version)
 9. Update the `build.gradle` to the next SNAPSHOT version.
 10. `git commit -am "Prepare next development version."`
 11. `git push && git push --tags`

If step 5 or 6 fails, fix the problem, commit, and start again at step 5.
