import sbt._

object Dependencies {

  object Versions {
    val catsEffect_1_x = "1.4.0"

    // Test
    val specs2 = "4.9.3"

    // Compiler
    val kindProjector    = "0.10.3"
    val betterMonadicFor = "0.3.0"
    val flyway            = "5.0.7"
  }

  object Libraries {
    lazy val flyway = "org.flywaydb"                % "flyway-core"                   % Versions.flyway
    lazy val catsEffect_1_x = "org.typelevel" %% "cats-effect" % Versions.catsEffect_1_x

    // Test
    lazy val specs2 = "org.specs2" %% "specs2-core" % Versions.specs2
    lazy val specs2ScalaCheck = "org.specs2" %% "specs2-scalacheck" % Versions.specs2

    // Compiler
    lazy val kindProjector    = "org.typelevel" %% "kind-projector"     % Versions.kindProjector
    lazy val betterMonadicFor = "com.olegpy"    %% "better-monadic-for" % Versions.betterMonadicFor
  }

}
