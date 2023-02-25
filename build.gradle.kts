// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0-alpha06").apply(false)
    id("com.android.library").version("8.1.0-alpha06").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    // New Plugin like hilt android gradle plugin and sqlDelight
    id("com.squareup.sqldelight").version("1.5.5").apply( false)
    id ("com.google.dagger.hilt.android").version( "2.45").apply(false)


}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


