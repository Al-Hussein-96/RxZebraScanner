# RxZebraScanner
Integrate EMDK zebra scanner with reactive programming (RxJava).

[see project on GitHub](https://github.com/Al-Hussein-96/RxZebraScanner).



# Download

First add kotlin to your project, in `build.gradle` **project level**:

```gradle
buildscript {
    ext.kotlin_version = '1.5.31'
    dependencies {
        ...
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}
...
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

Then add this line to `build.gradle` **app module level**:

```gradle
apply plugin: 'kotlin-android'
...
dependencies {
	implementation 'io.github.Al-Hussein-96:RxZebraScanner:1.0'
}

```

For **maven**

```maven
<dependency>
    <groupId>io.github.Al-Hussein-96</groupId>
    <artifactId>RxZebraScanner</artifactId>
    <version>1.0</version>
</dependency>
```
## TODO
* Complete working on it

# LICENSE
```

Copyright 2022 Mohammad AlHussain

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
