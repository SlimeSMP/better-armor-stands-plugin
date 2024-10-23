import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("xyz.jpenilla.run-paper") version "2.3.0" // Adds runServer and runMojangMappedServer tasks for testing
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1" // Generates plugin.yml based on the Gradle config
}

val groupStringSeparator = "."
val kebabcaseStringSeparator = "-"
val snakecaseStringSeparator = "_"

fun capitaliseFirstLetter(string: String): String {
  return string.first().uppercase() + string.slice(IntRange(1, string.length - 1))
}

fun snakecase(kebabcaseString: String): String {
  return kebabcaseString.lowercase().replace(kebabcaseStringSeparator, snakecaseStringSeparator).replace(" ", snakecaseStringSeparator)
}

fun pascalcase(kebabcaseString: String): String {
  var pascalCaseString = ""

  val splitString = kebabcaseString.split(kebabcaseStringSeparator)

  for (part in splitString) {
    pascalCaseString += capitaliseFirstLetter(part)
  }

  return pascalCaseString
}

val mainProjectAuthor = "Esoteric Organisation"
val topLevelDomain = "foundation"
val projectAuthors = listOfNotNull(mainProjectAuthor, "rolyPolyVole", "Esoteric Enderman")

group = topLevelDomain + groupStringSeparator + "esoteric"
version = "1.0.0-SNAPSHOT"
description = "A Minecraft plugin that adds the Bedrock Edition pose customisation to armor stands as well as allowing base-plate manipulation. Made for the SLIME SMP!"

val javaVersion = 21
val paperApiVersion = "1.21"

java {
  toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
}

dependencies {
  paperweight.paperDevBundle("$paperApiVersion-R0.1-SNAPSHOT")
}

tasks {
  compileJava {
    options.release = javaVersion
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }
}

bukkitPluginYaml {
  name = "BetterArmorStands"
  description = project.description
  authors = projectAuthors

  version = project.version.toString()
  apiVersion = paperApiVersion
  main = project.group.toString() + groupStringSeparator + "minecraft.smps.slime.plugins.armor.stands.better" + groupStringSeparator + pascalcase(rootProject.name)
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}
