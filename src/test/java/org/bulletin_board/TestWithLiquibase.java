package org.bulletin_board;

import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestWithLiquibase implements EnvironmentAware {
    private static final ResourceAccessor RESOURCE_ACCESSOR = new ClassLoaderResourceAccessor();

    private static Connection connection;
    private static Liquibase liquibase;
    private static Database database;

    private Environment environment;

    private boolean initialized;

    @Value("${spring.datasource.url}")
    String dbUrl;

    @Value("${spring.datasource.username}")
    String dbUser;

    @Value("${spring.datasource.password}")
    String dbPassword;

    protected abstract String getChangeSetPath();

    @BeforeAll
    public void prepareTestData() throws SQLException, LiquibaseException {
        if (initialized) {
            return;
        }
        initialized = true;

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

        String changelogPath = getChangeSetPath();
        ChangeLogParser parser = ChangeLogParserFactory.getInstance().getParser(changelogPath, RESOURCE_ACCESSOR);
        DatabaseChangeLog databaseChangeLog = parser.parse(changelogPath, new ChangeLogParameters(database), RESOURCE_ACCESSOR);

        liquibase = new Liquibase(databaseChangeLog, new ClassLoaderResourceAccessor(), database);
        liquibase.update("");
    }

    @AfterAll
    public static void removeTestData() throws LiquibaseException, SQLException {
        liquibase.rollback("testsTag", "");
        database.close();
        connection.close();
        liquibase = null;
        database = null;
        connection = null;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
