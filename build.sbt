import AssemblyKeys._

assemblySettings

name := "template-scala-parallel-classification"

scalaVersion := "2.11.8"

organization := "io.prediction"


libraryDependencies ++= Seq(
  "io.prediction"    %% "core"          % pioVersion.value % "provided",
  "org.apache.spark" %% "spark-core"    % "1.3.0" % "provided",
  "org.apache.spark" %% "spark-mllib"   % "1.3.0" % "provided",
  "org.apache.predictionio" %% "apache-predictionio-core" % "0.12.0-incubating" % "provided")
