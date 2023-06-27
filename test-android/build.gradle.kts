/*
 * Copyright (c) 2023 Toxicity
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
plugins {
    id("configuration")
}

repositories {
    google()
}

kmpConfiguration {
    this.configure {
        androidLibrary {
            kotlinJvmTarget = JavaVersion.VERSION_11
            compileSourceCompatibility = JavaVersion.VERSION_11
            compileTargetCompatibility = JavaVersion.VERSION_11

            android {
                namespace = "org.kotlincrypto.macs"
                compileSdk = 33

                defaultConfig {
                    minSdk = 14

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                libraryVariants.all {
                    generateBuildConfigProvider.configure {
                        enabled = false
                    }
                }
            }

            sourceSetTestInstrumented {
                kotlin.srcDir("src/androidInstrumentedTest/hmac-md")
                kotlin.srcDir("src/androidInstrumentedTest/hmac-sha1")
                kotlin.srcDir("src/androidInstrumentedTest/hmac-sha2")
                kotlin.srcDir("src/androidInstrumentedTest/hmac-sha3")
                kotlin.srcDir("src/androidInstrumentedTest/kmac")

                dependencies {
                    implementation(libs.androidx.test.runner)
                    implementation(libs.encoding.base16)
                    implementation(kotlin("test"))

                    implementation(project(":library:hmac:hmac-md"))
                    implementation(project(":library:hmac:hmac-sha1"))
                    implementation(project(":library:hmac:hmac-sha2"))
                    implementation(project(":library:hmac:hmac-sha3"))
                    implementation(project(":library:kmac"))
                    implementation(project(":tools:testing"))
                }
            }
        }
    }
}
