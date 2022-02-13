CREATE TABLE IF NOT EXISTS `teams` (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS `events` (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    id_first_team INTEGER NOT NULL,
    id_second_team INTEGER NOT NULL,
    date TEXT,
    FOREIGN KEY (id_first_team)
        REFERENCES teams (id)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (id_second_team)
        REFERENCES teams (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `event_results` (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_event INTEGER NOT NULL,
    first_team_score INTEGER NOT NULL,
    second_team_score INTEGER NOT NULL,
    id_winner INTEGER NOT NULL,
    FOREIGN KEY (id_event)
        REFERENCES events (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    FOREIGN KEY (id_winner)
        REFERENCES teams (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);
