import AssemblyKeys._

assemblySettings

name := "pio-template-text-clustering"

scalaVersion := "2.11.8"

organization := "com.kolibero"

libraryDependencies ++= Seq(
  "org.apache.predictionio" %% "apache-predictionio-core" % "0.12.0-incubating" % "provided",
  "org.apache.spark"        %% "spark-core"               % "2.1.1" % "provided",
  "org.apache.spark"        %% "spark-mllib"              % "2.1.1" % "provided",
  "org.scalatest"           %% "scalatest"                % "2.2.1" % "test")

// SparkContext is shared between all tests via SharedSingletonContext
parallelExecution in Test := false
