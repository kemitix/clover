graphs:
	mvn validate

install: .install

.install:
	mvn install
	touch .install

build:
	mvn package -DskipTests -Dpitest.skip

test:
	mvn test ${RUN_PARAMS}

issue-dir:
	@test ${CLOVER_DIR}

run: issue-dir install
	java -jar runner/target/clover-runner-*-jar-with-dependencies.jar

clean:
	mvn clean
	if [ -f .install ];then rm .install;fi

quick-build:
	mvn clean install -DskipTests -DskipITs

kill-runners:
	ps ax|grep clover-runne[r]|cut -b-5|xargs kill -9
