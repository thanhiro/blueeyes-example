resolvers ++= Seq(
	"sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
)

// To assemble runnable Service JAR, type 'assembly' in SBT prompt
libraryDependencies <+= (sbtVersion) { sv => "com.eed3si9n" %% "sbt-assembly" % ("sbt" + sv + "_0.4") }

// To generate idea project files, run 'gen-idea' at SBT prompt
libraryDependencies += "com.github.mpeltonen" %% "sbt-idea" % "0.10.0"
