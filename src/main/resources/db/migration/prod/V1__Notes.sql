CREATE TABLE "notes" (
    "id" VARCHAR(255) NOT NULL,
    "optlock" BIGINT NOT NULL,
    "title" VARCHAR(255) NOT NULL,
    "content" TEXT,
    "created_at" TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    PRIMARY KEY ("id")
)
