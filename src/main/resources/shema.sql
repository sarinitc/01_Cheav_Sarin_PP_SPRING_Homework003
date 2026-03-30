CREATE TABLE venues (
                        venue_id SERIAL PRIMARY KEY,
                        venue_name VARCHAR(100) NOT NULL,
                        location VARCHAR(150) NOT NULL
);

CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(100) NOT NULL,
                        event_date DATE NOT NULL,
                        venue_id INT,
                        CONSTRAINT fk_events_venue
                            FOREIGN KEY (venue_id)
                                REFERENCES venues(venue_id)
                                ON DELETE SET NULL
                                ON UPDATE CASCADE
);

CREATE TABLE attendees (
                           attendee_id SERIAL PRIMARY KEY,
                           attendee_name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE event_attendee (
                                attendee_id INT,
                                event_id INT,
                                PRIMARY KEY (attendee_id, event_id),
                                CONSTRAINT fk_event_attendee_attendee
                                    FOREIGN KEY (attendee_id)
                                        REFERENCES attendees(attendee_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE,
                                CONSTRAINT fk_event_attendee_event
                                    FOREIGN KEY (event_id)
                                        REFERENCES events(event_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE
);
INSERT INTO venues (venue_name, location) VALUES
                                              ('Tecno Hall', 'Phnom Penh'),
                                              ('Conference Room A', 'Takeo'),
                                              ('Grand Ballroom', 'Siem Reap'),
                                              ('City Hall', 'Battambang');

INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Tech Conference 2026', '2026-04-01', 1),
                                                          ('Startup Meetup', '2026-04-05', 2),
                                                          ('AI Workshop', '2026-04-10', 1),
                                                          ('Business Summit', '2026-04-15', 3),
                                                          ('Community Gathering', '2026-04-20', NULL);

INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Sarin Cheav', 'sarin@example.com'),
                                                 ('Emma Davis', 'emma@example.com'),
                                                 ('John Smith', 'john@example.com'),
                                                 ('Alice Johnson', 'alice@example.com'),
                                                 ('Michael Brown', 'michael@example.com');

