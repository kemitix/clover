FONT_FILE=-Dfont-file=${HOME}/cossmass/binder/fonts/Snowslider/SnowSL_Std.OTF
RUN_PARAMS=${FONT_FILE} #-Dclover.story-card.enabled=false #-Dclover.enable-paperback=false -Dclover.enable-paperback-preview=false

graphs:
	mvn validate

install: .install

.install:
	mvn install -DskipTests -Dpitest.skip
	touch .install

build:
	mvn package -DskipTests -Dpitest.skip

test:
	mvn test ${RUN_PARAMS}

issue-dir:
	@test ${CLOVER_DIR}

dev: issue-dir build
	mvn -pl runner quarkus:dev ${RUN_PARAMS}

run: issue-dir build
	( \
		cd runner/target/quarkus-app && \
		java \
			--class-path "../../../*/target/" \
			-jar quarkus-run.jar \
			${RUN_PARAMS} \
	)

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
