package me.hxkandwal.spring_mongo.dao.integration;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;

public abstract class AbstractEmbeddedMongoIntegrationTest {

	private static MongodProcess mongoProcess;
	
	private static Mongo mongo;

	@SuppressWarnings("deprecation")
	@BeforeClass
    public static void initializeDB() throws IOException {
		Properties configurationProperties = new Properties();
		configurationProperties.load(AbstractEmbeddedMongoIntegrationTest.class.getResourceAsStream("/dev/app.properties"));
		
		// clean dbpath directory
		FileUtils.cleanDirectory(new File(configurationProperties.getProperty("mongo.dbpath")));
		
        RuntimeConfig config = new RuntimeConfig();
        config.setExecutableNaming(new UserTempNaming());

        MongodStarter starter = MongodStarter.getInstance(config);

        MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, Integer.valueOf(configurationProperties.getProperty("mongo.port")), false, configurationProperties.getProperty("mongo.dbpath")));
        mongoProcess = mongoExecutable.start();
        
        mongo = new Mongo(configurationProperties.getProperty("mongo.host"), Integer.valueOf(configurationProperties.getProperty("mongo.port")));
        mongo.getDB(configurationProperties.getProperty("mongo.database"));
    }
	
    @AfterClass
    public static void shutdownDB() throws InterruptedException {
        mongo.close();
        mongoProcess.stop();
    }
    
}
