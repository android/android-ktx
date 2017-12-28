#AndroidKtx: Kotlin Extensions for Android

AndroidKtx is a set of Kotlin extensions for Android app development. The goal of AndroidKtx is to make Android development with Kotlin more concise, pleasant and idiomatic. It is an explicit goal of this project to **NOT** add any new feature to the existing Android APIs.

Sample use of AndroidKtx:
==TODO: add some sample code snippets.==

##Getting Started
To add AndroidKtx to your project, add the following to your app's dependencies:

```
repositories {
    google()
 }
 
 dependencies {
     implementation 'com.android.support:androidktx:<version>' // placeholder name
 }
```

Then in your .kt files where you'd like to use the Kotlin extensions, import the appropriate packages.

##Project Status: Preview
This project is currently in preview, and we are seeking feedback from the community.  We encourage you to try AndroidKtx so you can suggest ideas and report issues. Please see [How to Contribute section](#how-to-contribute) if you would ike to contribute.

During the preview period, the AndroidKtx APIs may change at anytime. When the project reaches a stable state, we will update it to version 1.0 (or later). At that point forward, we will be much more rigorous and careful about keeping API compatibility. 

==TBD: we may also decide to version it along with JetPack/Support Lib==

##Links
[AndroidKtx Guide](http://TBD) ==TODO==
[API reference documentation](http://TBD) ==TODO==
[Issue tracker: Report a defect or feature request](https://github.com/android/kotlin-extensions/issues/new)
[StackOverflow: Ask "how-to" and "why didn't it work" type questions](https://stackoverflow.com/questions/ask?tags=androidktx)

##How to Contribute
We welcome your contributions to this project. There are various ways to contribute:

####Reporting issues
Help improve the project by reporting issues that you find by filing a new issue at the [AndroidKtx issue tracker](https://github.com/android/kotlin-extensions/issues/new).

####Features suggestions
You can also add feature suggestions by filing a new issue at the [AndroidKtx issue tracker](https://github.com/android/kotlin-extensions/issues/new).


####Documentation
You can help by adding or improving existing documentation. Simply send us a pull request for us to consider your proposed changes.

####Bug fixes
For minor bug fixes that do not involve any changes to existing API, you can send a pull request to the project administrators.

####New API or API changes
TBD: need to discuss what the process should be.