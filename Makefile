.PHONY: all
all: backend frontend

.PHONY: backend
backend: build_backend_docker

.PHONY: frontend
frontend: build_frontend_docker

COMMIT		:= $(shell git rev-parse HEAD)
LATEST		:= latest

AWS_ECR_PUBLIC_REPOSITORY_PREFIX		:= public.ecr.aws/t0t2o6x5
AWS_PLAYGROUND_REGION					:= us-east-1

# >>> BACKEND APP >>> ##################################################################################################

BACKEND_REPOSITORY_NAME		:= academy/simple-webservice
BACKEND_REPOSITORY_URI		:= ${AWS_ECR_PUBLIC_REPOSITORY_PREFIX}/${BACKEND_REPOSITORY_NAME}

BACKEND_DOCKERFILE			:= src/main/docker/Dockerfile
BACKEND_IMAGE_COMMIT		:= ${BACKEND_REPOSITORY_NAME}:${COMMIT}

build_jar: test
	@cd simpleWebservice && \
		./gradlew clean build -x test

build_backend_docker: build_jar
	@cd simpleWebservice && \
		docker image build -f ${BACKEND_DOCKERFILE} -t ${BACKEND_IMAGE_COMMIT} .
	@docker tag ${BACKEND_IMAGE_COMMIT} ${BACKEND_REPOSITORY_NAME}:${LATEST}
	@docker tag ${BACKEND_IMAGE_COMMIT} ${BACKEND_REPOSITORY_URI}:${COMMIT}
	@docker tag ${BACKEND_IMAGE_COMMIT} ${BACKEND_REPOSITORY_URI}:${LATEST}

# >>> FRONTEND APP >>> #################################################################################################

FRONTEND_REPOSITORY_NAME		:= academy/simple-frontend
FRONTEND_REPOSITORY_URI			:= ${AWS_ECR_PUBLIC_REPOSITORY_PREFIX}/${FRONTEND_REPOSITORY_NAME}

FRONTEND_DOCKERFILE			:= Dockerfile
FRONTEND_IMAGE_COMMIT		:= ${FRONTEND_REPOSITORY_NAME}:${COMMIT}

build_frontend_docker:
	@cd simpleFrontEnd && \
		docker image build -f ${FRONTEND_DOCKERFILE} -t ${FRONTEND_IMAGE_COMMIT} .
	@docker tag ${FRONTEND_IMAGE_COMMIT} ${FRONTEND_REPOSITORY_NAME}:${LATEST}
	@docker tag ${FRONTEND_IMAGE_COMMIT} ${FRONTEND_REPOSITORY_URI}:${COMMIT}
	@docker tag ${FRONTEND_IMAGE_COMMIT} ${FRONTEND_REPOSITORY_URI}:${LATEST}

# >>> DOCKER COMPOSE >>> ###############################################################################################

.PHONY: up
up:
	@docker-compose up -d

.PHONY: down
down:
	@docker-compose down

.PHONY: logs
logs:
	@docker-compose logs -f --tail=50
.PHONY: ps
ps:
	@docker-compose ps

.PHONY: db
db:
	@DBPORTHOST="5432" DBPORTCONTAINER="5432" REDISPORTHOST="6379" REDISPORTCONTAINER="6379" ENV="DEV" \
	docker-compose up -d db cache

.PHONY: dbtest
dbtest:
	@DBPORTHOST="5430" DBPORTCONTAINER="5430" REDISPORTHOST="6380" REDISPORTCONTAINER="6380" ENV="TEST" \
	docker-compose up -d db cache

.PHONY: run-migration
run-migration:
	@cd simpleWebservice && \
    		./gradlew flywayClean && \
    		./gradlew flywayBaseline && \
    		./gradlew flywayMigrate
run-migration-test:
#$ gradle -Dflyway.user=myUser -Dflyway.schemas=schema1,schema2 -Dflyway.placeholders.keyABC=valueXYZ
	@cd simpleWebservice && \
    		./gradlew -Dflyway.configFiles='flyway-test.conf' flywayClean && \
    		./gradlew -Dflyway.configFiles='flyway-test.conf' flywayBaseline && \
    		./gradlew -Dflyway.configFiles='flyway-test.conf' flywayMigrate

.PHONY: test
test: dbtest run-migration-test run-test down

.PHONY: run-test
run-test:
	@cd simpleWebservice && \
    		./gradlew bootRun --args='--spring.profiles.active=test' clean test

