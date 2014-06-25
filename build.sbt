name := """crud"""

version := "0.7.3-SNAPSHOT"

libraryDependencies ++= Seq(
  javaCore, javaJdbc, javaEbean,
  "play2-crud" % "play2-crud_2.10" % "0.7.3-SNAPSHOT"
)

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.3"

play.Project.playJavaSettings

resolvers += "release repository" at  "http://hakandilek.github.com/maven-repo/releases/"

resolvers += "snapshot repository" at "http://hakandilek.github.com/maven-repo/snapshots/"

resolvers += "maven" at "http://search.maven.org/"

resolvers += "gradle" at "http://gradle.artifactoryonline.com/gradle/libs/"