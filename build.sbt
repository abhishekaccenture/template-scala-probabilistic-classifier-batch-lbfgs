import AssemblyKeys._

assemblySettings

name := "template-scala-parallel-classification"

scalaVersion := "2.11.8"

organization := "io.prediction"

libraryDependencies ++= Seq(
  "org.apache.predictionio" %% "apache-predictionio-core" % "0.12.0-incubating" % "provided",
  "org.apache.spark"        %% "spark-core"               % "2.1.1" % "provided",
  "org.apache.spark"        %% "spark-mllib"              % "2.1.1" % "provided",
   "io.prediction"    %% "core"          % pioVersion.value % "provided")
