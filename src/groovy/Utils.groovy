package iguana.src.groovy.Utils

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper


class FileUtils {
	
	static save(Object content, String filePath) {
		new File(filePath).write(new JsonBuilder(content).toPrettyString())
	}

	static Object load(String filePath) {
		return new JsonSlurper().parseText(new File(filePath).text)
	}
}








