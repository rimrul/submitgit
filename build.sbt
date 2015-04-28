name := "submitgit"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

updateOptions := updateOptions.value.withCachedResolution(true)

lazy val root = (project in file(".")).enablePlugins(
  PlayScala,
  BuildInfoPlugin
).settings(
  buildInfoKeys := Seq[BuildInfoKey](
    name,
    BuildInfoKey.constant("gitCommitId", Option(System.getenv("BUILD_VCS_NUMBER")) getOrElse(try {
      "git rev-parse HEAD".!!.trim
    } catch { case e: Exception => "unknown" }))
  ),
  buildInfoPackage := "app"
)

routesImport ++= Seq("controllers.ObjectIdBinder._","org.eclipse.jgit.lib.ObjectId")

libraryDependencies ++= Seq(
  cache,
  filters,
  ws,
  "com.typesafe.akka" %% "akka-agent" % "2.3.2",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "octicons" % "2.0.1",
  "org.kohsuke" % "github-api" % "1.66" exclude("org.jenkins-ci", "annotation-indexer"),
  "com.github.nscala-time" %% "nscala-time" % "1.8.0",
  "com.squareup.okhttp" % "okhttp" % "2.3.0",
  "com.squareup.okhttp" % "okhttp-urlconnection" % "2.3.0",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3-1",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r",
  "com.madgag.scala-git" %% "scala-git" % "2.9",
  "com.madgag.scala-git" %% "scala-git-test" % "2.9" % "test",
  "org.specs2" %% "specs2-core" % "2.4.17" % "test",
  "org.specs2" %% "specs2-junit" % "2.4.17" % "test",
  "com.amazonaws" % "aws-java-sdk-ses" % "1.9.31",
  "com.sun.mail" % "javax.mail" % "1.5.3"
)

sources in (Compile,doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false
