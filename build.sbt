name := "scala-train"

version := "1.0"

scalaVersion := "2.11.8"

//scalaのバージョンなどについては%%表記すれば設定値から読み込み
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.8"
)

