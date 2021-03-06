package flutterby.cats

import cats.data.Kleisli
import cats.effect.Sync
import org.flywaydb.core.api.configuration.{Configuration, FluentConfiguration}

package object config {
  type ConfigBuilder[F[_]] = Kleisli[F, FluentConfiguration, FluentConfiguration]

  import java.io.{File, OutputStream}
  import java.nio.charset.Charset
  import java.util.Properties

  import flutterby.core.jdk.CollectionConversions
  import javax.sql.DataSource
  import org.flywaydb.core.api.callback.Callback
  import org.flywaydb.core.api.resolver.MigrationResolver
  import org.flywaydb.core.api.{ClassProvider, Location, MigrationVersion, ResourceProvider}
  import cats.syntax.all._
  import org.flywaydb.core.api.migration.JavaMigration

  import flutterby.cats.FlutterbyCats.fromConfig
  import flutterby.core.Flutterby
  import org.flywaydb.core.api.migration.JavaMigration

  extension [F[_]](s: ConfigBuilder[F]) {
    def dataSource(url: String, user: String, password: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.dataSource[F](url, user, password).apply(s)

    def dataSource(dataSource: DataSource)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.dataSource(dataSource).apply(s)

    def dryRunOutput(dryRunOutput: OutputStream)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.dryRunOutput(dryRunOutput).apply(s)

    def dryRunOutput(dryRunOutput: File)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.dryRunOutput(dryRunOutput).apply(s)

    def dryRunOutput(dryRunOutputFileName: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.dryRunOutput(dryRunOutputFileName).apply(s)

    def errorOverrides(errorOverrides: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.errorOverrides(errorOverrides: _*).apply(s)

    def group(group: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.group(group).apply(s)

    def installedBy(installedBy: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.installedBy(installedBy).apply(s)

    def mixed(mixed: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.mixed(mixed).apply(s)

    def ignoreMissingMigrations(ignoreMissingMigrations: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.ignoreMissingMigrations(ignoreMissingMigrations).apply(s)

    def ignoreIgnoredMigrations(ignoreIgnoredMigrations: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.ignoreIgnoredMigrations(ignoreIgnoredMigrations).apply(s)

    def ignorePendingMigrations(ignorePendingMigrations: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.ignorePendingMigrations(ignorePendingMigrations).apply(s)

    def ignoreFutureMigrations(ignoreFutureMigrations: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.ignoreFutureMigrations(ignoreFutureMigrations).apply(s)

    def validateMigrationNaming(validateMigrationNaming: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.validateMigrationNaming(validateMigrationNaming).apply(s)

    def validateOnMigrate(validateOnMigrate: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.validateOnMigrate(validateOnMigrate).apply(s)

    def cleanOnValidationError(cleanOnValidationError: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.cleanOnValidationError(cleanOnValidationError).apply(s)

    def cleanDisabled(cleanDisabled: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.cleanDisabled(cleanDisabled).apply(s)

    def locations(locations: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.locations(locations: _*).apply(s)

    def locations(locations: List[Location])(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.locations(locations).apply(s)

    def encoding(encoding: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.encoding(encoding).apply(s)

    def encoding(encoding: Charset)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.encoding(encoding).apply(s)

    def defaultSchema(schema: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.defaultSchema(schema).apply(s)

    def schemas(schemas: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.schemas(schemas: _*).apply(s)

    def table(table: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.table(table).apply(s)

    def tablespace(tablespace: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.tablespace(tablespace).apply(s)

    def target(target: MigrationVersion)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.target(target).apply(s)

    def target(target: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.target(target).apply(s)

    def placeholderReplacement(placeholderReplacement: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.placeholderReplacement(placeholderReplacement).apply(s)

    def placeholders(placeholders: Map[String, String])(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.placeholders(placeholders).apply(s)

    def placeholderPrefix(placeholderPrefix: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.placeholderPrefix(placeholderPrefix).apply(s)

    def placeholderSuffix(placeholderSuffix: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.placeholderSuffix(placeholderSuffix).apply(s)

    def sqlMigrationPrefix(sqlMigrationPrefix: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.sqlMigrationPrefix(sqlMigrationPrefix).apply(s)

    def undoSqlMigrationPrefix(undoSqlMigrationPrefix: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.undoSqlMigrationPrefix(undoSqlMigrationPrefix).apply(s)

    def repeatableSqlMigrationPrefix(repeatableSqlMigrationPrefix: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.repeatableSqlMigrationPrefix(repeatableSqlMigrationPrefix).apply(s)

    def sqlMigrationSeparator(sqlMigrationSeparator: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.sqlMigrationSeparator(sqlMigrationSeparator).apply(s)

    def sqlMigrationSuffixes(sqlMigrationSuffixes: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.sqlMigrationSuffixes(sqlMigrationSuffixes: _*).apply(s)

    def javaMigrations(javaMigrations: JavaMigration*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.javaMigrations(javaMigrations: _*).apply(s)

    def connectRetries(connectRetries: Int)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.connectRetries(connectRetries).apply(s)

    def initSql(initSql: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.initSql(initSql).apply(s)

    def baselineVersion(baselineVersion: MigrationVersion)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.baselineVersion(baselineVersion).apply(s)

    def baselineVersion(baselineVersion: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.baselineVersion(baselineVersion).apply(s)

    def baselineDescription(baselineDescription: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.baselineDescription(baselineDescription).apply(s)

    def baselineOnMigrate(baselineOnMigrate: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.baselineOnMigrate(baselineOnMigrate).apply(s)

    def outOfOrder(outOfOrder: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.outOfOrder(outOfOrder).apply(s)

    def callbacks(callbacks: Callback*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.callbacks(callbacks: _*).apply(s)

    def callbackClassnames(callbacks: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.callbackClassnames(callbacks: _*).apply(s)

    def skipDefaultCallbacks(skipDefaultCallbacks: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.skipDefaultCallbacks(skipDefaultCallbacks).apply(s)

    def resolvers(resolvers: MigrationResolver*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.resolvers(resolvers: _*).apply(s)

    def resolverClassnames(resolvers: String*)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.resolverClassnames(resolvers: _*).apply(s)

    def skipDefaultResolvers(skipDefaultResolvers: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.skipDefaultResolvers(skipDefaultResolvers).apply(s)

    def stream(stream: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.stream(stream).apply(s)

    def batch(batch: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.batch(batch).apply(s)

    def oracleSqlplus(oracleSqlplus: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.oracleSqlplus(oracleSqlplus).apply(s)

    def licenseKey(licenseKey: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.licenseKey(licenseKey).apply(s)

    def configuration(properties: Properties)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.configuration(properties).apply(s)

    def configuration(props: Map[String, String])(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.configuration(props).apply(s)

    def loadDefaultConfigurationFiles(encoding: String)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.loadDefaultConfigurationFiles(encoding).apply(s)

    def envVars()(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.envVars().apply(s)

    def resourceProvider(resourceProvider: ResourceProvider)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.resourceProvider(resourceProvider).apply(s)

    def javaMigrationClassProvider(javaMigrationClassProvider: ClassProvider[JavaMigration])(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.javaMigrationClassProvider(javaMigrationClassProvider).apply(s)

    def createSchemas(createSchemas: Boolean)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      ConfigBuilder.createSchemas(createSchemas).apply(s)

    def updateConf(fn: FluentConfiguration => FluentConfiguration)(
      implicit F: Sync[F]
    ): ConfigBuilder[F] =
      s.flatMapF((f: FluentConfiguration) => F.delay(fn(f)))

    def build(
               implicit F: Sync[F]
             ): Config[F] =
      ConfigBuilder.build(s)

    def build(classLoader: ClassLoader)(
      implicit F: Sync[F]
    ): Config[F] =
      ConfigBuilder.build(s, classLoader)

    def load(implicit F: Sync[F]): F[Flutterby[F]]                           = FlutterbyCats.fromConfig[F](ConfigBuilder.build(s))
    def load(classLoader: ClassLoader)(implicit F: Sync[F]): F[Flutterby[F]] =
      fromConfig[F](ConfigBuilder.build(s, classLoader))
  }
}

package config {

  import java.io.{File, OutputStream}
  import java.nio.charset.Charset
  import java.util.Properties

  import flutterby.core.jdk.CollectionConversions
  import javax.sql.DataSource
  import org.flywaydb.core.api.callback.Callback
  import org.flywaydb.core.api.resolver.MigrationResolver
  import org.flywaydb.core.api.{ClassProvider, Location, MigrationVersion, ResourceProvider}
  import cats.syntax.all._
  import org.flywaydb.core.api.migration.JavaMigration

  sealed abstract class Config[F[_]] private[config] (val config: F[Configuration])

  object ConfigBuilder {

    def impl[F[_]](
        implicit F: Sync[F]
    ): ConfigBuilder[F] = Kleisli.ask[F, FluentConfiguration]

    type Endo[F[_]] = ConfigBuilder[F] => ConfigBuilder[F] // Endomorphism

    def dataSource[F[_]: Sync](url: String, user: String, password: String): Endo[F] =
      _.updateConf(_.dataSource(url, user, password))

    def dataSource[F[_]: Sync](dataSource: DataSource): Endo[F] =
      _.updateConf(_.dataSource(dataSource))

    def dryRunOutput[F[_]: Sync](dryRunOutput: OutputStream): Endo[F] =
      _.updateConf(_.dryRunOutput(dryRunOutput))

    def dryRunOutput[F[_]: Sync](dryRunOutput: File): Endo[F] =
      _.updateConf(_.dryRunOutput(dryRunOutput))

    def dryRunOutput[F[_]: Sync](dryRunOutputFileName: String): Endo[F] =
      _.updateConf(_.dryRunOutput(dryRunOutputFileName))

    def errorOverrides[F[_]: Sync](errorOverrides: String*): Endo[F] =
      _.updateConf(_.errorOverrides(errorOverrides: _*))

    def group[F[_]: Sync](group: Boolean): Endo[F] =
      _.updateConf(_.group(group))

    def installedBy[F[_]: Sync](installedBy: String): Endo[F] =
      _.updateConf(_.installedBy(installedBy))

    def mixed[F[_]: Sync](mixed: Boolean): Endo[F] =
      _.updateConf(_.mixed(mixed))

    def ignoreMissingMigrations[F[_]: Sync](ignoreMissingMigrations: Boolean): Endo[F] =
      _.updateConf(_.ignoreMissingMigrations(ignoreMissingMigrations))

    def ignoreIgnoredMigrations[F[_]: Sync](ignoreIgnoredMigrations: Boolean): Endo[F] =
      _.updateConf(_.ignoreIgnoredMigrations(ignoreIgnoredMigrations))

    def ignorePendingMigrations[F[_]: Sync](ignorePendingMigrations: Boolean): Endo[F] =
      _.updateConf(_.ignorePendingMigrations(ignorePendingMigrations))

    def ignoreFutureMigrations[F[_]: Sync](ignoreFutureMigrations: Boolean): Endo[F] =
      _.updateConf(_.ignoreFutureMigrations(ignoreFutureMigrations))

    def validateMigrationNaming[F[_]: Sync](validateMigrationNaming: Boolean): Endo[F] =
      _.updateConf(_.validateMigrationNaming(validateMigrationNaming))

    def validateOnMigrate[F[_]: Sync](validateOnMigrate: Boolean): Endo[F] =
      _.updateConf(_.validateOnMigrate(validateOnMigrate))

    def cleanOnValidationError[F[_]: Sync](cleanOnValidationError: Boolean): Endo[F] =
      _.updateConf(_.cleanOnValidationError(cleanOnValidationError))

    def cleanDisabled[F[_]: Sync](cleanDisabled: Boolean): Endo[F] =
      _.updateConf(_.cleanDisabled(cleanDisabled))

    def locations[F[_]: Sync](locations: String*): Endo[F] =
      _.updateConf(_.locations(locations: _*))

    def locations[F[_]: Sync](locations: List[Location]): Endo[F] =
      _.updateConf(_.locations(locations: _*))

    def encoding[F[_]: Sync](encoding: String): Endo[F] =
      _.updateConf(_.encoding(encoding))

    def encoding[F[_]: Sync](encoding: Charset): Endo[F] =
      _.updateConf(_.encoding(encoding))

    def defaultSchema[F[_]: Sync](schema: String): Endo[F] =
      _.updateConf(_.defaultSchema(schema))

    def schemas[F[_]: Sync](schemas: String*): Endo[F] =
      _.updateConf(_.schemas(schemas: _*))

    def table[F[_]: Sync](table: String): Endo[F] =
      _.updateConf(_.table(table))

    def tablespace[F[_]: Sync](tablespace: String): Endo[F] =
      _.updateConf(_.tablespace(tablespace))

    def target[F[_]: Sync](target: MigrationVersion): Endo[F] =
      _.updateConf(_.target(target))

    def target[F[_]: Sync](target: String): Endo[F] =
      _.updateConf(_.target(target))

    def placeholderReplacement[F[_]: Sync](placeholderReplacement: Boolean): Endo[F] =
      _.updateConf(_.placeholderReplacement(placeholderReplacement))

    def placeholders[F[_]: Sync](placeholders: Map[String, String]): Endo[F] =
      _.updateConf(_.placeholders(CollectionConversions.toJavaMap(placeholders)))

    def placeholderPrefix[F[_]: Sync](placeholderPrefix: String): Endo[F] =
      _.updateConf(_.placeholderPrefix(placeholderPrefix))

    def placeholderSuffix[F[_]: Sync](placeholderSuffix: String): Endo[F] =
      _.updateConf(_.placeholderSuffix(placeholderSuffix))

    def sqlMigrationPrefix[F[_]: Sync](sqlMigrationPrefix: String): Endo[F] =
      _.updateConf(_.sqlMigrationPrefix(sqlMigrationPrefix))

    def undoSqlMigrationPrefix[F[_]: Sync](undoSqlMigrationPrefix: String): Endo[F] =
      _.updateConf(_.undoSqlMigrationPrefix(undoSqlMigrationPrefix))

    def repeatableSqlMigrationPrefix[F[_]: Sync](repeatableSqlMigrationPrefix: String): Endo[F] =
      _.updateConf(_.repeatableSqlMigrationPrefix(repeatableSqlMigrationPrefix))

    def sqlMigrationSeparator[F[_]: Sync](sqlMigrationSeparator: String): Endo[F] =
      _.updateConf(_.sqlMigrationSeparator(sqlMigrationSeparator))

    def sqlMigrationSuffixes[F[_]: Sync](sqlMigrationSuffixes: String*): Endo[F] =
      _.updateConf(_.sqlMigrationSuffixes(sqlMigrationSuffixes: _*))

    def javaMigrations[F[_]: Sync](javaMigrations: JavaMigration*): Endo[F] =
      _.updateConf(_.javaMigrations(javaMigrations: _*))

    def connectRetries[F[_]: Sync](connectRetries: Int): Endo[F] =
      _.updateConf(_.connectRetries(connectRetries))

    def initSql[F[_]: Sync](initSql: String): Endo[F] =
      _.updateConf(_.initSql(initSql))

    def baselineVersion[F[_]: Sync](baselineVersion: MigrationVersion): Endo[F] =
      _.updateConf(_.baselineVersion(baselineVersion))

    def baselineVersion[F[_]: Sync](baselineVersion: String): Endo[F] =
      _.updateConf(_.baselineVersion(baselineVersion))

    def baselineDescription[F[_]: Sync](baselineDescription: String): Endo[F] =
      _.updateConf(_.baselineDescription(baselineDescription))

    def baselineOnMigrate[F[_]: Sync](baselineOnMigrate: Boolean): Endo[F] =
      _.updateConf(_.baselineOnMigrate(baselineOnMigrate))

    def outOfOrder[F[_]: Sync](outOfOrder: Boolean): Endo[F] =
      _.updateConf(_.outOfOrder(outOfOrder))

    def callbacks[F[_]: Sync](callbacks: Callback*): Endo[F] =
      _.updateConf(_.callbacks(callbacks: _*))

    def callbackClassnames[F[_]: Sync](callbacks: String*): Endo[F] =
      _.updateConf(_.callbacks(callbacks: _*))

    def skipDefaultCallbacks[F[_]: Sync](skipDefaultCallbacks: Boolean): Endo[F] =
      _.updateConf(_.skipDefaultCallbacks(skipDefaultCallbacks))

    def resolvers[F[_]: Sync](resolvers: MigrationResolver*): Endo[F] =
      _.updateConf(_.resolvers(resolvers: _*))

    def resolverClassnames[F[_]: Sync](resolvers: String*): Endo[F] =
      _.updateConf(_.resolvers(resolvers: _*))

    def skipDefaultResolvers[F[_]: Sync](skipDefaultResolvers: Boolean): Endo[F] =
      _.updateConf(_.skipDefaultResolvers(skipDefaultResolvers))

    def stream[F[_]: Sync](stream: Boolean): Endo[F] =
      _.updateConf(_.stream(stream))

    def batch[F[_]: Sync](batch: Boolean): Endo[F] =
      _.updateConf(_.batch(batch))

    def oracleSqlplus[F[_]: Sync](oracleSqlplus: Boolean): Endo[F] =
      _.updateConf(_.oracleSqlplus(oracleSqlplus))

    def licenseKey[F[_]: Sync](licenseKey: String): Endo[F] =
      _.updateConf(_.licenseKey(licenseKey))

    def configuration[F[_]: Sync](properties: Properties): Endo[F] =
      _.updateConf(_.configuration(properties))

    def configuration[F[_]: Sync](props: Map[String, String]): Endo[F] =
      _.updateConf(_.configuration(CollectionConversions.toJavaMap(props)))

    def loadDefaultConfigurationFiles[F[_]: Sync](encoding: String): Endo[F] =
      _.updateConf(_.loadDefaultConfigurationFiles(encoding))

    def envVars[F[_]: Sync](): Endo[F] =
      _.updateConf(_.envVars())

    def resourceProvider[F[_]: Sync](resourceProvider: ResourceProvider): Endo[F] =
      _.updateConf(_.resourceProvider(resourceProvider))

    def javaMigrationClassProvider[F[_]: Sync](javaMigrationClassProvider: ClassProvider[JavaMigration]): Endo[F] =
      _.updateConf(_.javaMigrationClassProvider(javaMigrationClassProvider))

    def createSchemas[F[_]: Sync](createSchemas: Boolean): Endo[F] =
      _.updateConf(_.createSchemas(createSchemas))

    def build[F[_]: Sync](s: ConfigBuilder[F]): Config[F]                           =
      new Config[F](s.run(new FluentConfiguration()).widen[Configuration]) {}

    def build[F[_]: Sync](s: ConfigBuilder[F], classLoader: ClassLoader): Config[F] =
      new Config[F](s.run(new FluentConfiguration(classLoader)).widen[Configuration]) {}


  }

}
