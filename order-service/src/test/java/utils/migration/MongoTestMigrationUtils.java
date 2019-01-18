package utils.migration;

import org.apache.commons.io.FileUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;

import java.io.File;
import java.io.IOException;

public class MongoTestMigrationUtils {
    /**
     * Clears the MongoDB test tables and repopulates them with fresh test data.
     * @param mongoTemplate reference to MongoTemplate object (used to execute the script)
     * @throws IOException Thrown if the script file is  missing.
     */
    public static void resetMongoDb(MongoTemplate mongoTemplate) throws IOException {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        File scriptFile = new File(mongoTemplate.getClass().getClassLoader()
                .getResource("db/migration/resetMongoDb.js").getFile());
        ExecutableMongoScript echoScript = new ExecutableMongoScript(FileUtils.readFileToString(scriptFile, "UTF-8"));
        scriptOps.execute(echoScript);
    }
}
