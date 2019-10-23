CREATE TABLE IF NOT EXISTS `Filter`
(
    `filter_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `query_id`  INTEGER                           NOT NULL,
    `state`     INTEGER                           NOT NULL,
    `distance`  INTEGER                           NOT NULL,
    `limit`     INTEGER                           NOT NULL,
    FOREIGN KEY (`query_id`) REFERENCES `Query` (`query_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_Filter_query_id` ON `Filter` (`query_id`);

CREATE TABLE IF NOT EXISTS `Query`
(
    `query_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `rawContactId` INTEGER                           NOT NULL,
    `name`         TEXT
);

CREATE TABLE IF NOT EXISTS `Sort`
(
    `sort_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `query_id`     INTEGER                           NOT NULL,
    `asc`          INTEGER                           NOT NULL,
    `alphabetical` INTEGER                           NOT NULL,
    FOREIGN KEY (`query_id`) REFERENCES `Query` (`query_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_Sort_query_id` ON `Sort` (`query_id`);


