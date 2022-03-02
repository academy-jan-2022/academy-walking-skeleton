CREATE TABLE "product"(
                           "id" SERIAL,
                           "name" VARCHAR(255) NOT NULL,
                           "quantity_per_unit" INTEGER NOT NULL,
                           "unit_price" INTEGER NOT NULL,
                           "units_in_stock" INTEGER NOT NULL,
                           "units_in_order" INTEGER NOT NULL,
                           "reorder_level" INTEGER NOT NULL,
                           "discontinued" BOOLEAN NOT NULL,
                           "category_id" INTEGER NOT NULL
);
ALTER TABLE
    "product" ADD PRIMARY KEY("id");