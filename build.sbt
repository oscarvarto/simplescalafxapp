name := "simplescalafxapp"

organization := "com.intersysconsulting"

version := "0.1.0"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalafx"   %% "scalafx"   % "11-R16",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test" //http://www.scalatest.org/download
)

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m =>
  "org.openjfx" % s"javafx-$m" % "11" classifier osName
)

shellPrompt := { _ => System.getProperty("user.name") + "> " }

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
mainClass in (Compile, run) := Some("com.intersysconsulting.ScalaFXHelloWorld")

// Fork a new JVM for 'run' and 'test:run' to avoid JavaFX double initialization problems
fork := true