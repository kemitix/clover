FONT_FILE=-Dfont-file=${HOME}/cossmass/binder/fonts/Snowslider/SnowSL_Std.OTF
RUN_PARAMS=${FONT_FILE}

graphs:
	mvn validate

install: .install

.install:
	mvn install -DskipTests -Dpitest.skip
	touch .install

test:
	mvn test ${RUN_PARAMS}

issue-dir:
	@test ${ISSUE_DIR}

dev: issue-dir
	mvn -pl runner quarkus:dev ${RUN_PARAMS}

run: issue-dir install
	java -jar runner/target/quarkus-app/quarkus-run.jar ${RUN_PARAMS}

clean:
	mvn clean
	if [ -f .install ];then rm .install;fi

quick-build:
	mvn clean install -DskipTests -DskipITs

kill-runners:
	ps ax|grep clover-runne[r]|cut -b-5|xargs kill -9

native:
	mvn verify -Pnative -DskipTests -DskipITs -Dpitest.skip

run-native:
	./runner/target/clover-runner
