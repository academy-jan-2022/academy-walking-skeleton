CREATE TABLE "address"(
                            "id" SERIAL,
                            "line_one" VARCHAR(255) NOT NULL,
                            "line_two" VARCHAR(255) NOT NULL,
                            "city" VARCHAR(255) NULL,
                            "region" VARCHAR(255) NOT NULL,
                            "post_code" VARCHAR(255) NOT NULL,
                            "country" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "address" ADD PRIMARY KEY("id");