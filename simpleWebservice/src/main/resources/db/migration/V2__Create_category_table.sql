CREATE TABLE "category"(
                             "id" SERIAL,
                             "name" VARCHAR(255) NOT NULL,
                             "description" TEXT NOT NULL,
                             "picture" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "category" ADD PRIMARY KEY("id");