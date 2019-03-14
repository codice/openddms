<!--
Copyright (c) 2019 Codice Foundation

Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
-->

# Open DDMS
Open DDMS is an open source library for parsing and creating DoD Discovery Metadata Specification (DDMS) documents.

## Features
* Validates DDMS objects on creation
* Immutable objects
* Builders for Java interop
* Kotlin DSL
* Parsing DDMS 2.0
* Writing DDMS 2.0

## Building
To build the project execute `gradlew build`. After the build, the distribution will be located in 
`build/libs/openddms-VERSION.jar`.

## Developing

#### Project Structure
This section will briefly talk about the project structure.

```
.
└── org.codice.ddms - Common interfaces for multiple DDMS versions.
   │
   ├── gmv.v3 - Classes for handling GML v3.
   │
   ├── v2 - Root package for all DDMS 2.0.
   │   │
   │   ├──  builder - Root package for all DDMS 2.0 builders. The package structure should reflect the root v2 package.
   │   │
   │   ├──  format - DDMS 2.0 format classes
   │   │
   │   ├──  reader - Classes for reading DDMS 2.0 in different formats
   │   │
   │   ├──  resource - DDMS 2.0 resource classes
   │   │
   │   ├──  security - DDMS 2.0 Security classes
   │   │
   │   ├──  summary - DDMS 2.0 summary classes
   │   │
   │   ├──  writer - Classes for writing DDMS 2.0 in different formats
   │
   └── xml.util - General helper classes for managing XML.
```


#### Formatting
The project uses [Spotless](https://github.com/diffplug/spotless) to ensure consistent style. Any style violations noted by Spotless can easily be resolved by running `./gradlew spotlessApply`.

## Copyright / License
Copyright (c) Codice Foundation

This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License
as published by the Free Software Foundation, either version 3 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
<http://www.gnu.org/licenses/lgpl.html>.